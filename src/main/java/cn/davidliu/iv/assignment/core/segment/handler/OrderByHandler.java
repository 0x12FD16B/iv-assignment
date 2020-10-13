package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.core.expression.OrderByFieldExpression;
import cn.davidliu.iv.assignment.core.expression.OrderDirection;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.core.segment.OrderBy;
import cn.davidliu.iv.assignment.core.segment.QuerySegment;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.support.util.ReflectUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Order By 语句段处理器
 *
 * @author David Liu
 */
public class OrderByHandler extends AbstractQuerySegmentHandlerInvokeTemplate {

    /**
     * Order By 语句段
     */
    private final OrderBy orderBy;

    public OrderByHandler(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public QuerySegment acquireCurrentQuerySegment() {
        return orderBy;
    }

    @Override
    protected void invokeExecuteSegment(QuerySegmentExecuteContext executeContext) {
        List<Object> inboundDataSet = executeContext.getInboundDataSet();
        ExpressionNode expressionNode = orderBy.segmentExpression();
        List<Object> outboundDataSet;
        if (CollectionUtils.isEmpty(inboundDataSet)) {
            outboundDataSet = Collections.emptyList();
        } else {
            OrderByFiledValuesNode head = new OrderByFiledValuesNode(null, null);
            OrderByFiledValuesNode t = head;
            Class<?> pojoClass = inboundDataSet.get(0).getClass();
            while (expressionNode != null) {
                OrderByFieldExpression orderByFieldExpression = (OrderByFieldExpression) expressionNode.getCurrentNode();
                String objectFieldName = orderByFieldExpression.getObjectFieldName();
                List<Object> sortedFiledValues = inboundDataSet.stream().map(e -> ReflectUtil.reflectInvokePojoGetterMethod(pojoClass, e, objectFieldName)).sorted().collect(Collectors.toList());
                if (orderByFieldExpression.getDirection() == OrderDirection.DESC) {
                    Collections.reverse(sortedFiledValues);
                }

                t.nextNode = new OrderByFiledValuesNode(objectFieldName, sortedFiledValues);
                t = head.nextNode;
                expressionNode = expressionNode.getNextNode();
            }

            t = head;
            outboundDataSet = this.sortRecursion(inboundDataSet, t.nextNode, pojoClass);
        }


        executeContext.setOutboundDataSet(outboundDataSet);
    }

    private List<Object> sortRecursion(List<Object> dataSet, OrderByFiledValuesNode valuesNode, Class<?> pojoClass) {
        // 递归终止条件
        if (valuesNode == null || CollectionUtils.isEmpty(dataSet)) {
            return dataSet;
        }

        if (dataSet.size() == 1) {
            return dataSet;
        }

        String orderByFieldName = valuesNode.fieldName;
        Map<Object, List<Object>> fieldGroupData = new HashMap<>();
        for (Object o : dataSet) {
            Object fieldValue = ReflectUtil.reflectInvokePojoGetterMethod(pojoClass, o, orderByFieldName);
            List<Object> valList = fieldGroupData.getOrDefault(fieldValue, new ArrayList<>());
            valList.add(o);
            fieldGroupData.put(fieldValue, valList);
        }

        List<Object> sortedValues = valuesNode.values;
        List<Object> res = new ArrayList<>();
        Set<Object> addedDataField = new HashSet<>();
        for (Object filedValue : sortedValues) {
            if (addedDataField.contains(filedValue) || !fieldGroupData.containsKey(filedValue)) {
                continue;
            }
            List<Object> tempRes = sortRecursion(fieldGroupData.get(filedValue), valuesNode.nextNode, pojoClass);
            res.addAll(tempRes);
            addedDataField.add(filedValue);
        }

        return res;
    }

    /**
     * 排序字段值集合
     */
    private static class OrderByFiledValuesNode {

        /**
         * 字段名
         */
        String fieldName;
        /**
         * 当前排序字段值
         */
        List<Object> values;
        /**
         * 下一个排序值节点
         */
        OrderByFiledValuesNode nextNode;

        public OrderByFiledValuesNode(String fieldName, List<Object> values) {
            this.values = values;
            this.fieldName = fieldName;
        }
    }
}

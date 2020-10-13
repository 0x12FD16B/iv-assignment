package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.core.expression.GroupFieldExpression;
import cn.davidliu.iv.assignment.core.expression.GroupFuncExecFieldExpression;
import cn.davidliu.iv.assignment.core.expression.func.singleton.GroupFuncExecInvokerContainer;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.core.segment.GroupBy;
import cn.davidliu.iv.assignment.core.segment.QuerySegment;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.support.util.ReflectUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * GroupBy 语句段处理器
 *
 * @author David Liu
 */
public class GroupByHandler extends AbstractQuerySegmentHandlerInvokeTemplate {

    /**
     * Group By 语句段
     */
    private final GroupBy groupBy;

    public GroupByHandler(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    @Override
    protected void invokeExecuteSegment(QuerySegmentExecuteContext executeContext) {
        // 解析 GroupBy 语句段表达式, 构造两个链表
        ExpressionNode expressionNode = groupBy.segmentExpression();
        GroupFieldExpressionNode fieldExpNodeHead = new GroupFieldExpressionNode();
        GroupFuncExecFieldExpressionNode funcExecFieldExpNodeHead = new GroupFuncExecFieldExpressionNode();
        GroupFuncExecFieldExpressionNode tFuncExecNode = funcExecFieldExpNodeHead;

        while (expressionNode != null) {
            if (expressionNode.getCurrentNode() instanceof GroupFieldExpression) {
                GroupFieldExpressionNode groupFieldExpressionNode = new GroupFieldExpressionNode();
                groupFieldExpressionNode.current = (GroupFieldExpression) expressionNode.getCurrentNode();
                fieldExpNodeHead.next = groupFieldExpressionNode;
            } else {
                GroupFuncExecFieldExpressionNode funcExecFieldExpressionNode = new GroupFuncExecFieldExpressionNode();
                funcExecFieldExpressionNode.current = (GroupFuncExecFieldExpression) expressionNode.getCurrentNode();
                tFuncExecNode.next = funcExecFieldExpressionNode;
            }
            expressionNode = expressionNode.getNextNode();
        }

        List<List<Object>> groupFuncExecDataSets = this.groupFieldRecursion(executeContext.getInboundDataSet(), fieldExpNodeHead.next);

        if (funcExecFieldExpNodeHead.next != null) {
            tFuncExecNode = funcExecFieldExpNodeHead;

            List<List<Object>> groupByResult = new ArrayList<>();
            while (tFuncExecNode.next != null) {
                GroupFuncExecFieldExpressionNode funcExecFieldExpressionNode = tFuncExecNode.next;
                List<Object> groupFuncResult = GroupFuncExecInvokerContainer.getInstance()
                        .acquireFuncExecInvoker(funcExecFieldExpressionNode.current.getGroupFuncType())
                        .invokeGroupFunc(groupFuncExecDataSets);
                groupByResult.add(groupFuncResult);
                tFuncExecNode = tFuncExecNode.next;
            }

            executeContext.setGroupFuncExecResult(groupByResult);
        }

        // TODO 完善 GroupBy
        executeContext.setOutboundDataSet(executeContext.getInboundDataSet());

    }

    /**
     * 递归计算分组数据集
     *
     * @param dataSet 传入数据集
     * @param node    分组字段链表
     * @return 分组数据集
     */
    private List<List<Object>> groupFieldRecursion(List<Object> dataSet, GroupFieldExpressionNode node) {
        // 递归终止条件
        if (node == null || CollectionUtils.isEmpty(dataSet)) {
            return Collections.singletonList(dataSet);
        }

        GroupFieldExpression current = node.current;
        String groupField = current.getObjectFieldName();
        Class<?> testPojoClass = dataSet.get(0).getClass();

        Map<Object, List<Object>> groupResult = new HashMap<>();
        for (Object o : dataSet) {
            Object fieldData = ReflectUtil.reflectInvokePojoGetterMethod(testPojoClass, o, groupField);
            List<Object> subList = groupResult.getOrDefault(fieldData, new ArrayList<>());
            subList.add(o);
            groupResult.put(fieldData, subList);
        }


        List<List<Object>> result = new ArrayList<>();
        GroupFieldExpressionNode temp = node.next;
        for (Map.Entry<Object, List<Object>> entry : groupResult.entrySet()) {
            result.addAll(groupFieldRecursion(entry.getValue(), temp));
        }

        return result;

    }

    @Override
    public QuerySegment acquireCurrentQuerySegment() {
        return groupBy;
    }

    /**
     * 分组字段链表节点
     */
    private static class GroupFieldExpressionNode {
        /**
         * 当前表达式
         */
        GroupFieldExpression current;
        /**
         * 下一个节点
         */
        GroupFieldExpressionNode next;
    }

    /**
     * 分组字段链表节点
     */
    private static class GroupFuncExecFieldExpressionNode {
        /**
         * 当前表达式
         */
        GroupFuncExecFieldExpression current;
        /**
         * 下一个节点
         */
        GroupFuncExecFieldExpressionNode next;
    }
}

package cn.davidliu.iv.assignment.core.expression.op.handler;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * OR 逻辑运算符结果连接处理器
 *
 * @author David Liu
 */
public class FilterOpResultOrJoinHandler implements FilterOpResultLogicJoinHandler {
    @Override
    public List<Object> invokeJoin(List<Object> left, List<Object> right) {
        if (CollectionUtils.isEmpty(left)) {
            return ObjectUtils.defaultIfNull(right, Collections.emptyList());
        }
        if (CollectionUtils.isEmpty(right)) {
            return ObjectUtils.defaultIfNull(left, Collections.emptyList());
        }
        return new ArrayList<>(CollectionUtils.union(left, right));
    }

    @Override
    public ExpressionLogicOpType supportExpressionLogicOpType() {
        return ExpressionLogicOpType.OR;
    }
}

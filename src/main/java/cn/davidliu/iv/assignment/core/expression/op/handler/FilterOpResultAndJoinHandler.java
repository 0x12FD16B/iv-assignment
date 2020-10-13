package cn.davidliu.iv.assignment.core.expression.op.handler;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AND 逻辑运算符结果连接处理器
 *
 * @author David Liu
 */
public class FilterOpResultAndJoinHandler implements FilterOpResultLogicJoinHandler {
    @Override
    public List<Object> invokeJoin(List<Object> left, List<Object> right) {
        if (CollectionUtils.isEmpty(left) || CollectionUtils.isEmpty(right)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(CollectionUtils.intersection(left, right));
    }

    @Override
    public ExpressionLogicOpType supportExpressionLogicOpType() {
        return ExpressionLogicOpType.AND;
    }
}

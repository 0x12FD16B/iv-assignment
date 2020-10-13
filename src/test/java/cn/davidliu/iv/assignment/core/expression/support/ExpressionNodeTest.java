package cn.davidliu.iv.assignment.core.expression.support;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import cn.davidliu.iv.assignment.core.expression.LimitExpression;
import cn.davidliu.iv.assignment.core.expression.OrderByFieldExpression;
import cn.davidliu.iv.assignment.core.expression.OrderDirection;
import cn.davidliu.iv.assignment.core.expression.builder.ExpressionNodeBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionNodeTest {

    @Test
    public void testExpressionNodeValidateMethodWhenNextNodePresent() {
        ExpressionNodeBuilder nodeBuilder = new ExpressionNodeBuilder()
                .firstExpression(new OrderByFieldExpression("aaa", OrderDirection.ASC))
                .appendExpression(new LimitExpression(1, 1), ExpressionLogicOpType.NONE);
        ExpressionNode node = nodeBuilder.buildResult();
        node.validate();
    }

    @Test
    public void testExpressionNodeConstructor() {
        ExpressionNode next = new ExpressionNode(new LimitExpression(1, 1), false, null, null);
        ExpressionNode current = new ExpressionNode(new OrderByFieldExpression("aaa", OrderDirection.ASC), true, next, ExpressionLogicOpType.NONE);
        Assert.assertNull(next.getLogicOpType());
        Assert.assertEquals(current.getLogicOpType(), ExpressionLogicOpType.NONE);
    }
}
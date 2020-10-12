package cn.davidliu.iv.assignment.struct.expression.builder;

import cn.davidliu.iv.assignment.struct.expression.ExpressionLogicOpType;
import cn.davidliu.iv.assignment.struct.expression.ExpressionNode;
import cn.davidliu.iv.assignment.struct.expression.GroupFieldExpression;
import cn.davidliu.iv.assignment.support.exceptions.AssertFailureException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExpressionNodeBuilderTest {

    private ExpressionNodeBuilder builder;

    @Before
    public void setUp() {
        builder = new ExpressionNodeBuilder();
    }

    @Test
    public void testFirstExpressionNotSetExceptionShouldThrown() {
        String expectErrorMsg = "表达式链表第一个节点未初始化";
        try {
            builder.buildResult();
        } catch (AssertFailureException e) {
            Assert.assertEquals(expectErrorMsg, e.getMessage());
        }
    }

    @Test
    public void testFirstExpressionSetNullExceptionShouldThrown() {
        String expectErrorMsg = "传入表达式不能为空";
        try {
            builder.firstExpression(null);
        } catch (AssertFailureException e) {
            Assert.assertEquals(expectErrorMsg, e.getMessage());
        }
    }

    @Test
    public void testFirstExpressionDuplicateSetExceptionShouldThrown() {
        String expectErrorMsg = "表达式链表第一个节点已经初始化";
        GroupFieldExpression expression = new GroupFieldExpression("field");
        try {
            builder.firstExpression(expression).firstExpression(expression);
        } catch (AssertFailureException e) {
            Assert.assertEquals(expectErrorMsg, e.getMessage());
        }
    }

    @Test
    public void testAppendExpressionPassNullExpressionExceptionShouldThrown() {
        String expectErrorMsg = "传入表达式不能为空";
        GroupFieldExpression expression = new GroupFieldExpression("field");
        try {
            builder.firstExpression(expression).appendExpression(null, null);
        } catch (AssertFailureException e) {
            Assert.assertEquals(expectErrorMsg, e.getMessage());
        }
    }

    @Test
    public void testAppendExpressionPassNullExpressionOpTypeExceptionShouldThrown() {
        String expectErrorMsg = "传入表达式逻辑连接符不能为空";
        GroupFieldExpression expression = new GroupFieldExpression("field");
        try {
            builder.firstExpression(expression).appendExpression(expression, null);
        } catch (AssertFailureException e) {
            Assert.assertEquals(expectErrorMsg, e.getMessage());
        }
    }

    @Test
    public void testAppendExpressionFirstExpressionNotSetExceptionShouldThrown() {
        String expectErrorMsg = "表达式链表第一个节点未初始化";
        GroupFieldExpression expression = new GroupFieldExpression("field");
        try {
            builder.appendExpression(expression, ExpressionLogicOpType.AND);
        } catch (AssertFailureException e) {
            Assert.assertEquals(expectErrorMsg, e.getMessage());
        }
    }

    @Test
    public void testAppendExpressionSuccess() {
        GroupFieldExpression expression = new GroupFieldExpression("field");
        ExpressionNode expressionNode = builder.firstExpression(expression).appendExpression(expression, ExpressionLogicOpType.AND).buildResult();
        Assert.assertNotNull(expressionNode);
        ExpressionNode t = expressionNode;
        while (t.isHasNext()) {
            Assert.assertEquals(t.getCurrentNode(), expression);
            t = t.getNextNode();
        }
    }
}
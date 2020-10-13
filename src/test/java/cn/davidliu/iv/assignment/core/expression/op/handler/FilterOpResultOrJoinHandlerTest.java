package cn.davidliu.iv.assignment.core.expression.op.handler;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class FilterOpResultOrJoinHandlerTest {

    @Test
    public void testSupportExpressionLogicOpType() {
        Assert.assertEquals(new FilterOpResultOrJoinHandler().supportExpressionLogicOpType(), ExpressionLogicOpType.OR);
    }

    @Test
    public void testInvokeJoinWhenLeftOrRightIsEitherEmptyAndAnotherShouldReturn() {
        List<Object> left = Collections.emptyList();
        List<Object> right = Collections.singletonList(new Object());
        FilterOpResultOrJoinHandler orJoinHandler = new FilterOpResultOrJoinHandler();
        Assert.assertEquals(orJoinHandler.invokeJoin(left, right), right);
        Assert.assertEquals(orJoinHandler.invokeJoin(right, left), right);
    }

    @Test
    public void testInvokeJoinWhenLeftAndRightBothNotEmptyTheUnionOfTwoListShouldReturn() {
        Object o1 = new Object();
        Object o2 = new Object();
        List<Object> left = Collections.singletonList(o1);
        List<Object> right = Collections.singletonList(o2);
        FilterOpResultOrJoinHandler orJoinHandler = new FilterOpResultOrJoinHandler();
        List<Object> result = orJoinHandler.invokeJoin(left, right);
        Assert.assertEquals(result.size(), 2);
        Assert.assertTrue(result.contains(o1) && result.contains(o2));
    }

}
package cn.davidliu.iv.assignment.core.expression.op.handler;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class FilterOpResultAndJoinHandlerTest {

    @Test
    public void testSupportExpressionLogicOpType() {
        Assert.assertEquals(new FilterOpResultAndJoinHandler().supportExpressionLogicOpType(), ExpressionLogicOpType.AND);
    }

    @Test
    public void testInvokeJoinWhenLeftIsEmptyEmptyListShouldReturn() {
        FilterOpResultAndJoinHandler andJoinHandler = new FilterOpResultAndJoinHandler();
        List<Object> left = Collections.emptyList();
        List<Object> right = Collections.singletonList(new Object());
        Assert.assertEquals(andJoinHandler.invokeJoin(left, right), Collections.emptyList());
        Assert.assertEquals(andJoinHandler.invokeJoin(right, left), Collections.emptyList());
    }

    @Test
    public void testInvokeJoinGeneralAndIntersectionOfListsShouldReturn() {
        FilterOpResultAndJoinHandler andJoinHandler = new FilterOpResultAndJoinHandler();
        Object o = new Object();
        List<Object> left = Collections.singletonList(o);
        List<Object> right = Collections.singletonList(o);
        List<Object> result = andJoinHandler.invokeJoin(left, right);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0), o);
    }

}
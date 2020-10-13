package cn.davidliu.iv.assignment.core.expression.op.singleton;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import org.junit.Assert;
import org.junit.Test;

public class FilterOpResultLogicJoinHandlerContainerTest {

    @Test
    public void testSingletonGetInstance() {
        FilterOpResultLogicJoinHandlerContainer joinHandlerContainer1 = FilterOpResultLogicJoinHandlerContainer.getInstance();
        FilterOpResultLogicJoinHandlerContainer joinHandlerContainer2 = FilterOpResultLogicJoinHandlerContainer.getInstance();
        Assert.assertSame(joinHandlerContainer1, joinHandlerContainer2);
    }

    @Test
    public void testAcquireOpInvoker() {
        Assert.assertNotNull(FilterOpResultLogicJoinHandlerContainer.getInstance().acquireOpInvoker(ExpressionLogicOpType.AND));
    }
}
package cn.davidliu.iv.assignment.core.expression.op.singleton;

import cn.davidliu.iv.assignment.core.expression.GroupFuncType;
import cn.davidliu.iv.assignment.core.expression.func.singleton.GroupFuncExecInvokerContainer;
import org.junit.Assert;
import org.junit.Test;

public class GroupFuncExecInvokerContainerTest {
    @Test
    public void testSingletonGetInstance() {
        GroupFuncExecInvokerContainer groupFuncExecInvokerContainer1 = GroupFuncExecInvokerContainer.getInstance();
        GroupFuncExecInvokerContainer groupFuncExecInvokerContainer2 = GroupFuncExecInvokerContainer.getInstance();
        Assert.assertSame(groupFuncExecInvokerContainer1, groupFuncExecInvokerContainer2);
    }

    @Test
    public void testAcquireOpInvoker() {
        Assert.assertNotNull(GroupFuncExecInvokerContainer.getInstance().acquireFuncExecInvoker(GroupFuncType.COUNT));
    }
}

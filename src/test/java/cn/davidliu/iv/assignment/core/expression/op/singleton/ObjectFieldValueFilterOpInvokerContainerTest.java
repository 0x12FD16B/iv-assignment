package cn.davidliu.iv.assignment.core.expression.op.singleton;

import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import org.junit.Assert;
import org.junit.Test;

public class ObjectFieldValueFilterOpInvokerContainerTest {

    @Test
    public void testSingletonGetInstance() {
        ObjectFieldValueFilterOpInvokerContainer invokerContainer1 = ObjectFieldValueFilterOpInvokerContainer.getInstance();
        ObjectFieldValueFilterOpInvokerContainer invokerContainer2 = ObjectFieldValueFilterOpInvokerContainer.getInstance();
        Assert.assertSame(invokerContainer1, invokerContainer2);
    }

    @Test
    public void acquireOpInvoker() {
        Assert.assertNotNull(ObjectFieldValueFilterOpInvokerContainer.getInstance().acquireOpInvoker(ObjectFieldValueFilterOpType.EQ));
    }
}
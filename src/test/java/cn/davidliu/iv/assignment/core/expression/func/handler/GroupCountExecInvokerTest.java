package cn.davidliu.iv.assignment.core.expression.func.handler;

import cn.davidliu.iv.assignment.core.expression.GroupFuncType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class GroupCountExecInvokerTest {

    @Test
    public void testSupportGroupFuncType() {
        Assert.assertEquals(new GroupCountExecInvoker().supportGroupFuncType(), GroupFuncType.COUNT);
    }

    @Test
    public void testInvokeGroupFuncWhenGivingEmptyListThenEmptyListShouldReturn() {
        Assert.assertEquals(new GroupCountExecInvoker().invokeGroupFunc(new ArrayList<>()).size(), 0);
    }

    @Test
    public void testInvokeGroupFuncWhenGivingNonEmptyListThenCorrectListSizeShouldReturn() {
        Assert.assertEquals(new GroupCountExecInvoker().invokeGroupFunc(Collections.singletonList(Collections.singletonList(new Object()))).size(), 1);
    }
}
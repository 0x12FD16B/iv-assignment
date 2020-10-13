package cn.davidliu.iv.assignment.core.expression.op;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectFieldValueNeFilterOpInvokerTest {
    @Test
    public void testInvokeFilterWhenGivingEmptyDataSetEmptyListShouldReturn() {
        ObjectFieldValueNeFilterOpInvoker invoker = new ObjectFieldValueNeFilterOpInvoker();
        List<Object> dataSet = new ArrayList<>();
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("", null, ObjectFieldValueFilterOpType.NE, ""));
        Assert.assertEquals(resSet, Collections.emptyList());
    }

    @Test
    public void testInvokeFilterCorrectResultShouldReturn() {
        ObjectFieldValueNeFilterOpInvoker invoker = new ObjectFieldValueNeFilterOpInvoker();
        Integer intVal = 1;
        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(intVal);
        dataSet.add(testPojo);
        TestPojo testPojo1 = new TestPojo();
        testPojo1.setIntField(2);
        dataSet.add(testPojo1);
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.NE, intVal));
        Assert.assertEquals(1, resSet.size());
    }

    @Test
    public void testSupportObjectFieldValueFilterOpType() {
        Assert.assertEquals(new ObjectFieldValueNeFilterOpInvoker().supportObjectFieldValueFilterOpType(), ObjectFieldValueFilterOpType.NE);
    }

}
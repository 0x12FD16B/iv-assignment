package cn.davidliu.iv.assignment.core.expression.op;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectFieldValueInFilterOpInvokerTest {
    @Test
    public void testInvokeFilterWhenGivingEmptyDataSetEmptyListShouldReturn() {
        ObjectFieldValueInFilterOpInvoker invoker = new ObjectFieldValueInFilterOpInvoker();
        List<Object> dataSet = new ArrayList<>();
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("", null, ObjectFieldValueFilterOpType.IN, Collections.emptyList()));
        Assert.assertEquals(resSet, Collections.emptyList());
    }

    @Test
    public void testInvokeFilterCorrectResultShouldReturn() {
        ObjectFieldValueInFilterOpInvoker invoker = new ObjectFieldValueInFilterOpInvoker();
        Integer intVal = 1;
        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(intVal);
        dataSet.add(testPojo);
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.IN, Collections.singleton(intVal)));
        Assert.assertTrue(resSet.size() == 1 && resSet.get(0) == testPojo);
    }

    @Test
    public void testSupportObjectFieldValueFilterOpType() {
        Assert.assertEquals(new ObjectFieldValueInFilterOpInvoker().supportObjectFieldValueFilterOpType(), ObjectFieldValueFilterOpType.IN);
    }

}
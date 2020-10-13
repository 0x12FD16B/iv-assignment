package cn.davidliu.iv.assignment.core.expression.op;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectFieldValueGeFilterOpInvokerTest {

    @Test
    public void testInvokeFilterWhenGivingEmptyDataSetEmptyListShouldReturn() {
        ObjectFieldValueGeFilterOpInvoker invoker = new ObjectFieldValueGeFilterOpInvoker();
        List<Object> dataSet = new ArrayList<>();
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("", null, ObjectFieldValueFilterOpType.GE, ""));
        Assert.assertEquals(resSet, Collections.emptyList());
    }

    @Test
    public void testInvokeFilterCorrectResultShouldReturn() {
        ObjectFieldValueGeFilterOpInvoker invoker = new ObjectFieldValueGeFilterOpInvoker();
        Integer intVal = 1;
        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(intVal);
        dataSet.add(testPojo);
        TestPojo testPojo1 = new TestPojo();
        testPojo1.setIntField(-1);
        dataSet.add(testPojo1);
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.GE, 0));
        Assert.assertTrue(resSet.size() == 1 && resSet.get(0) == testPojo);
    }

    @Test
    public void testSupportObjectFieldValueFilterOpType() {
        Assert.assertEquals(new ObjectFieldValueGeFilterOpInvoker().supportObjectFieldValueFilterOpType(), ObjectFieldValueFilterOpType.GE);
    }

}
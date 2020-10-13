package cn.davidliu.iv.assignment.core.expression.op;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectFieldValueIsNullFilterOpInvokerTest {

    @Test
    public void testInvokeFilterWhenGivingEmptyDataSetEmptyListShouldReturn() {
        ObjectFieldValueIsNullFilterOpInvoker invoker = new ObjectFieldValueIsNullFilterOpInvoker();
        List<Object> dataSet = new ArrayList<>();
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("", null, ObjectFieldValueFilterOpType.IS_NULL, null));
        Assert.assertEquals(resSet, Collections.emptyList());
    }

    @Test
    public void testInvokeFilterCorrectResultShouldReturn() {
        ObjectFieldValueIsNullFilterOpInvoker invoker = new ObjectFieldValueIsNullFilterOpInvoker();
        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        dataSet.add(testPojo);
        List<Object> resSet = invoker.invokeFilter(dataSet, new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.IS_NULL, null));
        Assert.assertTrue(resSet.size() == 1 && resSet.get(0) == testPojo);
    }

    @Test
    public void testSupportObjectFieldValueFilterOpType() {
        Assert.assertEquals(new ObjectFieldValueIsNullFilterOpInvoker().supportObjectFieldValueFilterOpType(), ObjectFieldValueFilterOpType.IS_NULL);
    }

}
package cn.davidliu.iv.assignment.core.expression;

import cn.davidliu.iv.assignment.support.exceptions.AssertFailureException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ObjectFieldValueFilterOpExpressionTest {

    @Test
    public void testObjectFieldAllowedAlwaysReturnTrue() {
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression(null, null, null, null);
        Assert.assertTrue(objectFieldValueFilterOpExpression.objectFieldAllowed());
    }

    @Test
    public void testValidateObjectFieldNameAssertError() {
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression(null, null, null, null);
        String errMsg = "对象值字段与传入值逻辑运算表达式中 [对象字段名称] 不能为空";
        try {
            objectFieldValueFilterOpExpression.validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), errMsg);
        }
    }

    @Test
    public void testValidateObjectFieldValueClassAssertError() {
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression("aaa", null, null, null);
        String errMsg = "对象值字段与传入值逻辑运算表达式中 [对象值类型 class] 不能为空";
        try {
            objectFieldValueFilterOpExpression.validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), errMsg);
        }
    }

    @Test
    public void generalValidateTest() {
        new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.EQ, "null").validate();
        new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.NE, "null").validate();
        new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.LE, "null").validate();
        new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.LT, "null").validate();
        new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.GT, "null").validate();
        new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.NOT_IN, Arrays.asList("aaa", "bbb")).validate();
    }

    @Test
    public void testValidateObjectFieldOpTypeCoverageGe() {
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.GE, "null");
        objectFieldValueFilterOpExpression.validate();
    }

    @Test
    public void testValidateObjectFieldOpTypeCoverageIn() {
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.IN, Arrays.asList("aaa", "bbb"));
        objectFieldValueFilterOpExpression.validate();
    }

    @Test
    public void testGetObjectFieldName() {
        String fieldName = "aaa";
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression(fieldName, String.class, ObjectFieldValueFilterOpType.IN, Arrays.asList("aaa", "bbb"));
        Assert.assertEquals(fieldName, objectFieldValueFilterOpExpression.getObjectFieldName());
    }

    @Test
    public void testGetObjectFieldValueClass() {
        Class<?> objectFiledValueFilterOpTypeClass = String.class;
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression("aaa", objectFiledValueFilterOpTypeClass, ObjectFieldValueFilterOpType.IN, Arrays.asList("aaa", "bbb"));
        Assert.assertEquals(objectFiledValueFilterOpTypeClass, objectFieldValueFilterOpExpression.getObjectFieldValueClass());
    }

    @Test
    public void testGetOpType() {
        ObjectFieldValueFilterOpType in = ObjectFieldValueFilterOpType.IN;
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression("aaa", String.class, in, Arrays.asList("aaa", "bbb"));
        Assert.assertEquals(in, objectFieldValueFilterOpExpression.getOpType());
    }

    @Test
    public void testGetOpParticipateValue() {
        List<String> values = Arrays.asList("aaa", "bbb");
        ObjectFieldValueFilterOpExpression objectFieldValueFilterOpExpression = new ObjectFieldValueFilterOpExpression("aaa", String.class, ObjectFieldValueFilterOpType.IN, values);
        Assert.assertEquals(values, objectFieldValueFilterOpExpression.getOpParticipateValue());
    }
}
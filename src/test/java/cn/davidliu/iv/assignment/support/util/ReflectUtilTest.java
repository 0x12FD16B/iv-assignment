package cn.davidliu.iv.assignment.support.util;

import cn.davidliu.iv.assignment.TestPojo;
import org.junit.Assert;
import org.junit.Test;

public class ReflectUtilTest {

    @Test
    public void testReflectInvokePojoGetterMethod() {
        String stringField = "hello";
        Integer intField = 1;
        Long longField = 1L;
        Double doubleField = Double.valueOf("1");
        TestPojo testPojo = new TestPojo();
        testPojo.setStringField(stringField);
        testPojo.setIntField(intField);
        testPojo.setDoubleField(doubleField);
        testPojo.setLongField(longField);
        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, testPojo, "stringField"), stringField);
        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, testPojo, "intField"), intField);
        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, testPojo, "longField"), longField);
        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, testPojo, "doubleField"), doubleField);
    }

    @Test
    public void coverageWithReflectUtilConstruct() {
        new ReflectUtil();
    }
}
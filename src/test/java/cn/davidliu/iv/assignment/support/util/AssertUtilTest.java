package cn.davidliu.iv.assignment.support.util;

import cn.davidliu.iv.assignment.support.exceptions.AssertFailureException;
import org.junit.Assert;
import org.junit.Test;

public class AssertUtilTest {

    @Test
    public void testConstructor() {
        AssertUtil assertUtil = new AssertUtil();
        Assert.assertNotNull(assertUtil);
    }

    @Test
    public void assertNotNull() {
        String assertFailureMessage = "assert failure message";
        try {
            AssertUtil.assertNotNull(null, assertFailureMessage);
        } catch (AssertFailureException e) {
            Assert.assertEquals(assertFailureMessage, e.getMessage());
        }
    }

    @Test
    public void assertTrue() {
        String assertFailureMessage = "assert failure message";
        try {
            AssertUtil.assertTrue(false, assertFailureMessage);
        } catch (AssertFailureException e) {
            Assert.assertEquals(assertFailureMessage, e.getMessage());
        }
    }

    @Test
    public void assertFalse() {
        String assertFailureMessage = "assert failure message";
        try {
            AssertUtil.assertFalse(true, assertFailureMessage);
        } catch (AssertFailureException e) {
            Assert.assertEquals(assertFailureMessage, e.getMessage());
        }
    }

    @Test
    public void assertNull() {
        String assertFailureMessage = "assert failure message";
        try {
            AssertUtil.assertNull(Object.class, assertFailureMessage);
        } catch (AssertFailureException e) {
            Assert.assertEquals(assertFailureMessage, e.getMessage());
        }

    }
}
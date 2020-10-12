package cn.davidliu.iv.assignment.support.util;

import cn.davidliu.iv.assignment.support.exceptions.AssertFailureException;

/**
 * 断言工具类
 *
 * @author David Liu
 */
public final class AssertUtil {

    /**
     * 断言传入对象不为空
     *
     * @param o       断言对象
     * @param message 断言失败信息
     */
    public static void assertNotNull(Object o, String message) {
        assertTrue(o != null, message);
    }

    /**
     * 断言传入对象为空
     *
     * @param o       断言对象
     * @param message 断言失败信息
     */
    public static void assertNull(Object o, String message) {
        assertTrue(o == null, message);
    }

    /**
     * 断言传入 boolean 为 true
     *
     * @param b       断言 boolean
     * @param message 断言失败信息
     */
    public static void assertTrue(boolean b, String message) {
        if (!b) throw new AssertFailureException(message);
    }

    /**
     * 断言传入 boolean 为 false
     *
     * @param b       断言 boolean
     * @param message 断言失败信息
     */
    public static void assertFalse(boolean b, String message) {
        assertTrue(!b, message);
    }
}

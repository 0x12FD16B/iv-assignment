package cn.davidliu.iv.assignment.support.log;

/**
 * 日志接口
 *
 * @author David Liu
 */
public interface Log {
    /**
     * 输出日常日志, 并且打印异常调用栈信息
     *
     * @param s 异常信息
     * @param e 异常对象
     */
    void error(String s, Throwable e);

    /**
     * 输出异常信息
     *
     * @param s 异常信息
     */
    void error(String s);

    /**
     * 输出提示信息日志
     *
     * @param s 输出提示信息日志
     */
    void info(String s);

    /**
     * 输出调试级别信息日志
     *
     * @param s 输出提示信息日志
     */
    void debug(String s);

    /**
     * 输出警示日志信息
     *
     * @param s 警示日志信息
     */
    void warn(String s);
}

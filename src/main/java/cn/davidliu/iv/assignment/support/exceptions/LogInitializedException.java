package cn.davidliu.iv.assignment.support.exceptions;

/**
 * 日志框架初始化异常
 *
 * @author David Liu
 */
public class LogInitializedException extends RuntimeException {
    private static final long serialVersionUID = 4170241058755046175L;

    public LogInitializedException(String message) {
        super(message);
    }

    public LogInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogInitializedException(Throwable cause) {
        super(cause);
    }
}

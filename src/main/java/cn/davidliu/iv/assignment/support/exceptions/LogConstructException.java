package cn.davidliu.iv.assignment.support.exceptions;

/**
 * 日志构造异常
 *
 * @author David Liu
 */
public class LogConstructException extends RuntimeException {
    private static final long serialVersionUID = 6734548897789832183L;

    public LogConstructException(String message) {
        super(message);
    }

    public LogConstructException(String message, Throwable cause) {
        super(message, cause);
    }
}

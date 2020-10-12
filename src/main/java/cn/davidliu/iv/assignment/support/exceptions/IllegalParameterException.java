package cn.davidliu.iv.assignment.support.exceptions;

/**
 * 参数不合法异常
 *
 * @author David Liu
 */
public class IllegalParameterException extends RuntimeException {
    private static final long serialVersionUID = 4641512348978788340L;

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }
}

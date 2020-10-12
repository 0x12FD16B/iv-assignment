package cn.davidliu.iv.assignment.support.exceptions;

/**
 * 表达式状态异常
 *
 * @author David Liu
 */
public class ExpressionIllegalStateException extends RuntimeException {
    private static final long serialVersionUID = -3657913716825725980L;

    public ExpressionIllegalStateException(String message) {
        super(message);
    }

    public ExpressionIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionIllegalStateException(Throwable cause) {
        super(cause);
    }
}

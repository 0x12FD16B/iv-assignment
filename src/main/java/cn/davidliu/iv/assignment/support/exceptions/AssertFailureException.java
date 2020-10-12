package cn.davidliu.iv.assignment.support.exceptions;

/**
 * 断言失败异常
 *
 * @author David Liu
 */
public class AssertFailureException extends RuntimeException {
    private static final long serialVersionUID = -1075111534353573354L;

    public AssertFailureException(String message) {
        super(message);
    }

    public AssertFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssertFailureException(Throwable cause) {
        super(cause);
    }
}

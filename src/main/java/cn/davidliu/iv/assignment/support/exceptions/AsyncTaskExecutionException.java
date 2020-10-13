package cn.davidliu.iv.assignment.support.exceptions;

/**
 * 异步任务执行异常
 *
 * @author David Liu
 */
public class AsyncTaskExecutionException extends RuntimeException {
    private static final long serialVersionUID = 4808967603303523605L;

    public AsyncTaskExecutionException(String message) {
        super(message);
    }

    public AsyncTaskExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AsyncTaskExecutionException(Throwable cause) {
        super(cause);
    }
}

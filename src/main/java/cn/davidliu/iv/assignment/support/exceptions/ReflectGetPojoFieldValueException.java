package cn.davidliu.iv.assignment.support.exceptions;

/**
 * 反射获取 Pojo 字段异常
 *
 * @author David Liu
 */
public class ReflectGetPojoFieldValueException extends RuntimeException {
    private static final long serialVersionUID = -8829373503878101251L;

    public ReflectGetPojoFieldValueException(String message) {
        super(message);
    }

    public ReflectGetPojoFieldValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectGetPojoFieldValueException(Throwable cause) {
        super(cause);
    }
}

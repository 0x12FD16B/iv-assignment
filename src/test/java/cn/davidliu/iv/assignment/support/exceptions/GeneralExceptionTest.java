package cn.davidliu.iv.assignment.support.exceptions;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

/**
 * 异常测试用例
 *
 * @author David Liu
 */
public class GeneralExceptionTest {
    private static final String EXPECTED_MESSAGE = "Test Message";
    private static final Exception EXPECTED_CAUSE = new Exception("Nested Exception");

    @Test
    public void shouldInstantiateAndThrowAllCustomExceptions() throws Exception {
        Class<?>[] exceptionTypes = {
                LogConstructException.class,
                LogInitializedException.class
        };
        for (Class<?> exceptionType : exceptionTypes) {
            testExceptionConstructors(exceptionType);
        }

    }

    private void testExceptionConstructors(Class<?> exceptionType) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Exception e = (Exception) exceptionType.getConstructor(String.class).newInstance(EXPECTED_MESSAGE);
        testThrowException(e);
        e = (Exception) exceptionType.getConstructor(String.class, Throwable.class).newInstance(EXPECTED_MESSAGE, EXPECTED_CAUSE);
        testThrowException(e);
        e = (Exception) exceptionType.getConstructor(Throwable.class).newInstance(EXPECTED_CAUSE);
        testThrowException(e);
    }

    private void testThrowException(Exception thrown) {
        try {
            throw thrown;
        } catch (Exception caught) {
            assertEquals(thrown.getMessage(), caught.getMessage());
            assertEquals(thrown.getCause(), caught.getCause());
        }
    }
}

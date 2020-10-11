package cn.davidliu.iv.assignment.support.log;

import cn.davidliu.iv.assignment.support.exceptions.LogConstructException;
import cn.davidliu.iv.assignment.support.exceptions.LogInitializedException;

import java.lang.reflect.Constructor;

/**
 * 日志工厂
 *
 * @author David Liu
 */
public final class LogFactory {

    private static Constructor<? extends Log> logConstructor;

    static {
        tryImplementation(LogFactory::useStdOutLogging);
    }

    private LogFactory() {
        // 私有化构造函数, 不允许初始化
    }

    public static Log getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    public static Log getLog(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (Throwable t) {
            throw new LogConstructException("Error creating logger for logger " + logger + ".  Cause: " + t, t);
        }
    }

    public static synchronized void useStdOutLogging() {
        setImplementation(StdOutImpl.class);
    }

    private static void setImplementation(Class<? extends Log> implClass) {
        try {
            logConstructor = implClass.getConstructor(String.class);
        } catch (Throwable t) {
            throw new LogInitializedException("设置日志框架实现出错, 异常: " + t, t);
        }
    }

    private static void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Throwable t) {
                throw new LogInitializedException("日志框架初始化出错");
            }
        }
    }
}

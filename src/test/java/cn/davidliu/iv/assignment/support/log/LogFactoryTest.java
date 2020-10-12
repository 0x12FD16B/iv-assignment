package cn.davidliu.iv.assignment.support.log;


import org.junit.Assert;
import org.junit.Test;

public class LogFactoryTest {

    @Test
    public void shouldUseStdOutLogging() {
        LogFactory.useStdOutLogging();
        Log log = LogFactory.getLog(Object.class);
        logSomething(log);
        Assert.assertEquals(log.getClass().getName(), StdOutImpl.class.getName());
    }

    @Test
    public void testLogFactoryCouldConstruct() {
        new LogFactory();
    }

    private void logSomething(Log log) {
        log.debug("Debug message.");
        log.info("Info message");
        log.warn("Warning message.");
        log.error("Error message.");
        log.error("Error with Exception.", new Exception("Test exception."));
    }
}
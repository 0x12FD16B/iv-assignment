package cn.davidliu.iv.assignment.support.log;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LogFactoryTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldUseStdOutLogging() {
        LogFactory.useStdOutLogging();
        Log log = LogFactory.getLog(Object.class);
        logSomething(log);
        Assert.assertEquals(log.getClass().getName(), StdOutImpl.class.getName());
    }

    private void logSomething(Log log) {
        log.debug("Debug message.");
        log.info("Info message");
        log.warn("Warning message.");
        log.error("Error message.");
        log.error("Error with Exception.", new Exception("Test exception."));
    }
}
package cn.davidliu.iv.assignment.support.thread;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadFactory;

public class WhereExpressionExecuteThreadFactoryTest {

    @Test
    public void testNewThread() {
        String expectThreadName = "Where-Segment-Execute-Thread-1";
        ThreadFactory threadFactory = new WhereExpressionExecuteThreadFactory();
        Thread thread = threadFactory.newThread(() -> System.out.println("Empty Runnable"));
        Assert.assertEquals(thread.getName(), expectThreadName);
    }
}
package cn.davidliu.iv.assignment.support.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Where 语句段表达式执行线程工厂
 *
 * @author David Liu
 */
public class WhereExpressionExecuteThreadFactory implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "Where-Segment-Execute-Thread-" + counter.getAndIncrement());
    }
}

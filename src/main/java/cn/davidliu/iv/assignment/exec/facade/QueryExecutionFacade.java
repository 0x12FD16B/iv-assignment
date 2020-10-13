package cn.davidliu.iv.assignment.exec.facade;

import cn.davidliu.iv.assignment.core.segment.GroupBy;
import cn.davidliu.iv.assignment.core.segment.Limit;
import cn.davidliu.iv.assignment.core.segment.OrderBy;
import cn.davidliu.iv.assignment.core.segment.Where;
import cn.davidliu.iv.assignment.core.segment.handler.GroupByHandler;
import cn.davidliu.iv.assignment.core.segment.handler.LimitHandler;
import cn.davidliu.iv.assignment.core.segment.handler.OrderByHandler;
import cn.davidliu.iv.assignment.core.segment.handler.WhereHandler;
import cn.davidliu.iv.assignment.core.segment.handler.chain.IQuerySegmentHandlerChain;
import cn.davidliu.iv.assignment.core.segment.handler.chain.QuerySegmentHandlerChain;
import cn.davidliu.iv.assignment.exec.interfaces.IQueryExecutionFacade;
import cn.davidliu.iv.assignment.support.thread.WhereExpressionExecuteThreadFactory;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 查询执行 Facade
 *
 * @author David Liu
 */
public class QueryExecutionFacade implements IQueryExecutionFacade {
    @Override
    public List<Object> query(List<Object> dataSet, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit) {
        // 从系统变量中获取 Where 表达式计算线程池配置, 如果未获取到配置, 适用默认值
        int whereExpressionExecuteThreadExecutorCoreSize = Integer.parseInt(System.getProperty("where.expression.invoke.thread-pool.core-size", "10"));
        int whereExpressionExecuteThreadExecutorMaxSize = Integer.parseInt(System.getProperty("where.expression.invoke.thread-pool.max-size", "100"));
        long whereExpressionExecuteThreadExecutorKeepAliveTime = Long.parseLong(System.getProperty("where.expression.invoke.thread-pool.keep-alive-seconds", "60"));

        ThreadPoolExecutor whereExpressionExecuteThreadExecutor = new ThreadPoolExecutor(
                whereExpressionExecuteThreadExecutorCoreSize, whereExpressionExecuteThreadExecutorMaxSize, whereExpressionExecuteThreadExecutorKeepAliveTime,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new WhereExpressionExecuteThreadFactory());

        WhereHandler whereHandler = where == null ? null : new WhereHandler(where, whereExpressionExecuteThreadExecutor);
        GroupByHandler groupByHandler = groupBy == null ? null : new GroupByHandler(groupBy);
        OrderByHandler orderByHandler = orderBy == null ? null : new OrderByHandler(orderBy);
        LimitHandler limitHandler = limit == null ? null : new LimitHandler(limit);

        IQuerySegmentHandlerChain handlerChain = QuerySegmentHandlerChain.make(whereHandler, groupByHandler, orderByHandler, limitHandler);
        return handlerChain.invokeQuery(dataSet);
    }
}

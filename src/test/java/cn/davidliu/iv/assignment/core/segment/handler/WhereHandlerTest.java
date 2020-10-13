package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import cn.davidliu.iv.assignment.core.expression.builder.ExpressionNodeBuilder;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.core.segment.Where;
import cn.davidliu.iv.assignment.support.thread.WhereExpressionExecuteThreadFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WhereHandlerTest {

    @Test
    public void acquireCurrentQuerySegment() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression(null, null, null, null));
        Where where = new Where(builder.buildResult());
        WhereHandler whereHandler = new WhereHandler(where, null);
        Assert.assertEquals(whereHandler.acquireCurrentQuerySegment(), where);
    }

    @Test
    public void testInvokeExecuteSegmentParallelWhenGivingSingleWhereField() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 1));
        Where where = new Where(builder.buildResult());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new WhereExpressionExecuteThreadFactory());
        WhereHandler whereHandler = new WhereHandler(where, executor);

        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(1);
        dataSet.add(testPojo);

        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(dataSet);

        whereHandler.invokeExecuteSegment(context);

        List<Object> outboundDataSet = context.getOutboundDataSet();
        Assert.assertEquals(outboundDataSet.size(), 1);
        Assert.assertEquals(outboundDataSet.get(0), testPojo);
    }

    @Test
    public void testInvokeExecuteSegmentParallelWhenGivingMultiWhereField() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 1))
                .appendExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 2), ExpressionLogicOpType.OR);
        Where where = new Where(builder.buildResult());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new WhereExpressionExecuteThreadFactory());
        WhereHandler whereHandler = new WhereHandler(where, executor);

        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo1 = new TestPojo();
        testPojo1.setIntField(1);
        dataSet.add(testPojo1);
        TestPojo testPojo2 = new TestPojo();
        testPojo2.setIntField(2);
        dataSet.add(testPojo2);


        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(dataSet);

        whereHandler.invokeExecuteSegment(context);

        List<Object> outboundDataSet = context.getOutboundDataSet();
        Assert.assertEquals(outboundDataSet.size(), 2);
        Assert.assertTrue(outboundDataSet.contains(testPojo1) && outboundDataSet.contains(testPojo2));
    }

    @Test
    public void testInvokeExecuteSegmentParallelWhenEmptyDataSetThenEmptyListShouldReturn() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 1));
        Where where = new Where(builder.buildResult());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new WhereExpressionExecuteThreadFactory());
        WhereHandler whereHandler = new WhereHandler(where, executor);

        List<Object> dataSet = new ArrayList<>();

        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(dataSet);

        whereHandler.invokeExecuteSegment(context);

        List<Object> outboundDataSet = context.getOutboundDataSet();
        Assert.assertEquals(outboundDataSet.size(), 0);
    }

    @Test
    public void testInvokeExecuteSegmentSerialWhenEmptyDataSetThenEmptyListShouldReturn() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 1));
        Where where = new Where(builder.buildResult());

        WhereHandler whereHandler = new WhereHandler(where, null);

        List<Object> dataSet = new ArrayList<>();

        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(dataSet);

        whereHandler.invokeExecuteSegment(context);

        List<Object> outboundDataSet = context.getOutboundDataSet();
        Assert.assertEquals(outboundDataSet.size(), 0);
    }

    @Test
    public void testInvokeExecuteSegmentSerialWhenGivingSingleWhereField() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 1));
        Where where = new Where(builder.buildResult());

        WhereHandler whereHandler = new WhereHandler(where, null);

        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(1);
        dataSet.add(testPojo);

        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(dataSet);

        whereHandler.invokeExecuteSegment(context);

        List<Object> outboundDataSet = context.getOutboundDataSet();
        Assert.assertEquals(outboundDataSet.size(), 1);
        Assert.assertEquals(outboundDataSet.get(0), testPojo);
    }

    @Test
    public void testInvokeExecuteSegmentSerialWhenGivingMultiWhereField() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 1))
                .appendExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 2), ExpressionLogicOpType.OR);
        Where where = new Where(builder.buildResult());

        WhereHandler whereHandler = new WhereHandler(where, null);

        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo1 = new TestPojo();
        testPojo1.setIntField(1);
        dataSet.add(testPojo1);
        TestPojo testPojo2 = new TestPojo();
        testPojo2.setIntField(2);
        dataSet.add(testPojo2);


        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(dataSet);

        whereHandler.invokeExecuteSegment(context);

        List<Object> outboundDataSet = context.getOutboundDataSet();
        Assert.assertEquals(outboundDataSet.size(), 2);
        Assert.assertTrue(outboundDataSet.contains(testPojo1) && outboundDataSet.contains(testPojo2));
    }
}
package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.core.expression.OrderByFieldExpression;
import cn.davidliu.iv.assignment.core.expression.OrderDirection;
import cn.davidliu.iv.assignment.core.expression.builder.ExpressionNodeBuilder;
import cn.davidliu.iv.assignment.core.segment.OrderBy;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.support.util.ReflectUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType.COMMA_SEP;

public class OrderByHandlerTest {

    @Test
    public void acquireCurrentQuerySegment() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        OrderBy orderBy = new OrderBy(builder.firstExpression(new OrderByFieldExpression("field1", OrderDirection.ASC)).buildResult());
        OrderByHandler orderByHandler = new OrderByHandler(orderBy);
        Assert.assertEquals(orderByHandler.acquireCurrentQuerySegment(), orderBy);
    }

    @Test
    public void testOrderByIntFieldAsc() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        OrderBy orderBy = new OrderBy(builder.firstExpression(new OrderByFieldExpression("intField", OrderDirection.ASC)).buildResult());
        OrderByHandler orderByHandler = new OrderByHandler(orderBy);

        List<Object> inboundDataSet = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TestPojo testPojo = new TestPojo();
            testPojo.setIntField(10 - i);
            inboundDataSet.add(testPojo);
        }

        QuerySegmentExecuteContext executeContext = new QuerySegmentExecuteContext();
        executeContext.setInboundDataSet(inboundDataSet);

        orderByHandler.invokeExecuteSegment(executeContext);

        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, executeContext.getOutboundDataSet().get(0), "intField"), 1);
    }

    @Test
    public void testOrderByIntFieldDesc() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        OrderBy orderBy = new OrderBy(builder.firstExpression(new OrderByFieldExpression("intField", OrderDirection.DESC)).buildResult());
        OrderByHandler orderByHandler = new OrderByHandler(orderBy);

        List<Object> inboundDataSet = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TestPojo testPojo = new TestPojo();
            testPojo.setIntField(i);
            inboundDataSet.add(testPojo);
        }

        QuerySegmentExecuteContext executeContext = new QuerySegmentExecuteContext();
        executeContext.setInboundDataSet(inboundDataSet);

        orderByHandler.invokeExecuteSegment(executeContext);

        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, executeContext.getOutboundDataSet().get(0), "intField"), 9);
    }

    @Test
    public void testOrderByIntFieldDescCoverageDuplicateElement() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        OrderBy orderBy = new OrderBy(builder.firstExpression(new OrderByFieldExpression("intField", OrderDirection.DESC)).buildResult());
        OrderByHandler orderByHandler = new OrderByHandler(orderBy);

        List<Object> inboundDataSet = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TestPojo testPojo = new TestPojo();
            testPojo.setIntField(i);
            inboundDataSet.add(testPojo);
        }
        // 添加个重复的 0
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(0);
        inboundDataSet.add(testPojo);
        inboundDataSet.add(testPojo);

        QuerySegmentExecuteContext executeContext = new QuerySegmentExecuteContext();
        executeContext.setInboundDataSet(inboundDataSet);

        orderByHandler.invokeExecuteSegment(executeContext);

        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, executeContext.getOutboundDataSet().get(0), "intField"), 9);
    }

    @Test
    public void testOrderByMultiFiled() throws NoSuchAlgorithmException {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        ExpressionNode expressionNode = builder.firstExpression(new OrderByFieldExpression("intField", OrderDirection.DESC))
                .appendExpression(new OrderByFieldExpression("longField", OrderDirection.ASC), COMMA_SEP)
                .buildResult();
        OrderBy orderBy = new OrderBy(expressionNode);
        OrderByHandler orderByHandler = new OrderByHandler(orderBy);

        List<Object> inboundDataSet = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TestPojo testPojo = new TestPojo();
            testPojo.setIntField(i);
            testPojo.setLongField(SecureRandom.getInstanceStrong().nextLong());
            inboundDataSet.add(testPojo);
        }
        // 添加个重复的 0
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(0);
        testPojo.setLongField(0L);
        inboundDataSet.add(testPojo);
        inboundDataSet.add(testPojo);

        QuerySegmentExecuteContext executeContext = new QuerySegmentExecuteContext();
        executeContext.setInboundDataSet(inboundDataSet);

        orderByHandler.invokeExecuteSegment(executeContext);

        Assert.assertEquals(ReflectUtil.reflectInvokePojoGetterMethod(TestPojo.class, executeContext.getOutboundDataSet().get(0), "intField"), 9);
    }

    @Test
    public void testOrderByInboundEmptyDataSet() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        OrderBy orderBy = new OrderBy(builder.firstExpression(new OrderByFieldExpression("intField", OrderDirection.ASC)).buildResult());
        OrderByHandler orderByHandler = new OrderByHandler(orderBy);

        List<Object> inboundDataSet = new ArrayList<>();

        QuerySegmentExecuteContext executeContext = new QuerySegmentExecuteContext();
        executeContext.setInboundDataSet(inboundDataSet);

        orderByHandler.invokeExecuteSegment(executeContext);

        Assert.assertTrue(CollectionUtils.isEmpty(executeContext.getOutboundDataSet()));
    }
}
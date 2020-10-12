package cn.davidliu.iv.assignment.struct.segment.handler;

import cn.davidliu.iv.assignment.struct.expression.ExpressionNode;
import cn.davidliu.iv.assignment.struct.expression.LimitExpression;
import cn.davidliu.iv.assignment.struct.expression.builder.ExpressionNodeBuilder;
import cn.davidliu.iv.assignment.struct.segment.Limit;
import cn.davidliu.iv.assignment.struct.segment.QuerySegmentExecuteContext;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LimitHandlerTest {

    @Test
    public void acquireCurrentQuerySegment() {

    }

    @Test
    public void invokeExecuteSegmentWhenLimit0EmptyResultSetShouldReturn() {
        ExpressionNodeBuilder expressionNodeBuilder = new ExpressionNodeBuilder();
        expressionNodeBuilder.firstExpression(new LimitExpression(0, 1));
        ExpressionNode expressionNode = expressionNodeBuilder.buildResult();
        Limit limit = new Limit(expressionNode);
        LimitHandler limitHandler = new LimitHandler(limit);

        List<Object> dataSet = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            dataSet.add(String.valueOf(i));
        }

        QuerySegmentExecuteContext querySegmentExecuteContext = new QuerySegmentExecuteContext();
        querySegmentExecuteContext.setInboundDataSet(dataSet);

        limitHandler.executeSegment(querySegmentExecuteContext);

        Assert.assertTrue(CollectionUtils.isEmpty(querySegmentExecuteContext.getOutboundDataSet()));
    }

    @Test
    public void invokeExecuteSegmentWhenInboundDataSetEmptyEmptyResultSetShouldReturn() {
        ExpressionNodeBuilder expressionNodeBuilder = new ExpressionNodeBuilder();
        expressionNodeBuilder.firstExpression(new LimitExpression(1, 1));
        ExpressionNode expressionNode = expressionNodeBuilder.buildResult();
        Limit limit = new Limit(expressionNode);
        LimitHandler limitHandler = new LimitHandler(limit);

        QuerySegmentExecuteContext querySegmentExecuteContext = new QuerySegmentExecuteContext();
        querySegmentExecuteContext.setInboundDataSet(Collections.emptyList());

        limitHandler.executeSegment(querySegmentExecuteContext);

        Assert.assertTrue(CollectionUtils.isEmpty(querySegmentExecuteContext.getOutboundDataSet()));
    }

    @Test
    public void invokeExecuteSegmentWhenOffsetExceedInboundDataSetSizeEmptyEmptyResultSetShouldReturn() {
        ExpressionNodeBuilder expressionNodeBuilder = new ExpressionNodeBuilder();
        expressionNodeBuilder.firstExpression(new LimitExpression(1, 101));
        ExpressionNode expressionNode = expressionNodeBuilder.buildResult();
        Limit limit = new Limit(expressionNode);
        LimitHandler limitHandler = new LimitHandler(limit);
        QuerySegmentExecuteContext querySegmentExecuteContext = new QuerySegmentExecuteContext();
        List<Object> dataSet = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataSet.add(String.valueOf(i));
        }
        querySegmentExecuteContext.setInboundDataSet(dataSet);
        limitHandler.executeSegment(querySegmentExecuteContext);
        Assert.assertTrue(CollectionUtils.isEmpty(querySegmentExecuteContext.getOutboundDataSet()));
    }

    @Test
    public void invokeExecuteSegmentWhenLimitEqEmptyResultSetSizeOutboundSizeShouldEqInboundSize() {
        ExpressionNodeBuilder expressionNodeBuilder = new ExpressionNodeBuilder();
        expressionNodeBuilder.firstExpression(new LimitExpression(100, 0));
        ExpressionNode expressionNode = expressionNodeBuilder.buildResult();
        Limit limit = new Limit(expressionNode);
        LimitHandler limitHandler = new LimitHandler(limit);

        List<Object> dataSet = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            dataSet.add(String.valueOf(i));
        }

        QuerySegmentExecuteContext querySegmentExecuteContext = new QuerySegmentExecuteContext();
        querySegmentExecuteContext.setInboundDataSet(dataSet);

        limitHandler.executeSegment(querySegmentExecuteContext);

        Assert.assertEquals(querySegmentExecuteContext.getOutboundDataSet().size(), querySegmentExecuteContext.getInboundDataSet().size());
    }

    @Test
    public void invokeExecuteSegmentWhenLimit5Offset5() {
        ExpressionNodeBuilder expressionNodeBuilder = new ExpressionNodeBuilder();
        expressionNodeBuilder.firstExpression(new LimitExpression(5, 5));
        ExpressionNode expressionNode = expressionNodeBuilder.buildResult();
        Limit limit = new Limit(expressionNode);
        LimitHandler limitHandler = new LimitHandler(limit);

        List<Object> dataSet = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            dataSet.add(String.valueOf(i));
        }

        QuerySegmentExecuteContext querySegmentExecuteContext = new QuerySegmentExecuteContext();
        querySegmentExecuteContext.setInboundDataSet(dataSet);

        limitHandler.executeSegment(querySegmentExecuteContext);
        Assert.assertEquals("5", querySegmentExecuteContext.getOutboundDataSet().get(0));
    }
}
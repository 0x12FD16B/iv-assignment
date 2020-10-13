package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import cn.davidliu.iv.assignment.core.expression.GroupFieldExpression;
import cn.davidliu.iv.assignment.core.expression.GroupFuncExecFieldExpression;
import cn.davidliu.iv.assignment.core.expression.GroupFuncType;
import cn.davidliu.iv.assignment.core.expression.builder.ExpressionNodeBuilder;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.core.segment.GroupBy;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupByHandlerTest {

    @Test
    public void testInvokeExecuteSegmentWhenGivenEmptyListThenEmptyListShouldReturn() {
        ExpressionNodeBuilder nodeBuilder = new ExpressionNodeBuilder();
        ExpressionNode groupExpressionNode = nodeBuilder.firstExpression(new GroupFieldExpression("intField"))
                .appendExpression(new GroupFuncExecFieldExpression("intField", GroupFuncType.COUNT), ExpressionLogicOpType.NONE)
                .buildResult();
        GroupBy groupBy = new GroupBy(groupExpressionNode);
        GroupByHandler handler = new GroupByHandler(groupBy);
        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(Collections.emptyList());
        handler.invokeExecuteSegment(context);
        Assert.assertEquals(context.getOutboundDataSet().size(), 0);
    }

    @Test
    public void testInvokeExecuteSegmentWhenGivenNonEmptyListAndGroupCountFuncThenCorrectResultShouldReturn() {
        ExpressionNodeBuilder nodeBuilder = new ExpressionNodeBuilder();
        ExpressionNode groupExpressionNode = nodeBuilder.firstExpression(new GroupFieldExpression("intField"))
                .appendExpression(new GroupFuncExecFieldExpression("intField", GroupFuncType.COUNT), ExpressionLogicOpType.NONE)
                .buildResult();
        GroupBy groupBy = new GroupBy(groupExpressionNode);
        GroupByHandler handler = new GroupByHandler(groupBy);
        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();

        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(1);
        dataSet.add(testPojo);
        context.setInboundDataSet(dataSet);
        handler.invokeExecuteSegment(context);
        Assert.assertEquals(context.getGroupFuncExecResult().size(), 1);
        Assert.assertEquals(context.getGroupFuncExecResult().get(0).size(), 1);
        Assert.assertEquals(context.getGroupFuncExecResult().get(0).get(0), 1);
    }

    @Test
    public void testAcquireCurrentQuerySegment() {
        ExpressionNodeBuilder nodeBuilder = new ExpressionNodeBuilder();
        ExpressionNode groupExpressionNode = nodeBuilder.firstExpression(new GroupFieldExpression("intField"))
                .appendExpression(new GroupFuncExecFieldExpression("intField", GroupFuncType.COUNT), ExpressionLogicOpType.NONE)
                .buildResult();
        GroupBy groupBy = new GroupBy(groupExpressionNode);
        GroupByHandler handler = new GroupByHandler(groupBy);
        Assert.assertEquals(handler.acquireCurrentQuerySegment(), groupBy);
    }
}
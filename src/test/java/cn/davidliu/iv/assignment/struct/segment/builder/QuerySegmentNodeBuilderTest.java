package cn.davidliu.iv.assignment.struct.segment.builder;

import cn.davidliu.iv.assignment.struct.expression.GroupFieldExpression;
import cn.davidliu.iv.assignment.struct.expression.builder.ExpressionNodeBuilder;
import cn.davidliu.iv.assignment.struct.segment.GroupBy;
import cn.davidliu.iv.assignment.struct.segment.QuerySegmentNode;
import org.junit.Assert;
import org.junit.Test;

public class QuerySegmentNodeBuilderTest {

    @Test
    public void testAppendSegmentOnceTime() {
        QuerySegmentNodeBuilder builder = new QuerySegmentNodeBuilder();
        ExpressionNodeBuilder expressionNodeBuilder = new ExpressionNodeBuilder();
        expressionNodeBuilder.firstExpression(new GroupFieldExpression("field"));
        GroupBy groupBy = new GroupBy(expressionNodeBuilder.buildResult());
        QuerySegmentNode querySegmentNode = builder.appendSegment(groupBy).buildResult();
        Assert.assertNotNull(querySegmentNode);
        Assert.assertEquals(groupBy, querySegmentNode.getSegment());
    }

    @Test
    public void testAppendSegmentTwiceTimeShouldCoverAppendEleBranch() {
        QuerySegmentNodeBuilder builder = new QuerySegmentNodeBuilder();
        ExpressionNodeBuilder expressionNodeBuilder = new ExpressionNodeBuilder();
        expressionNodeBuilder.firstExpression(new GroupFieldExpression("field"));
        GroupBy groupBy = new GroupBy(expressionNodeBuilder.buildResult());
        QuerySegmentNode querySegmentNode = builder.appendSegment(groupBy).appendSegment(groupBy).buildResult();
        Assert.assertNotNull(querySegmentNode);
        QuerySegmentNode t = querySegmentNode;
        while (t != null) {
            Assert.assertEquals(groupBy, t.getSegment());
            t = t.getNext();
        }
    }
}
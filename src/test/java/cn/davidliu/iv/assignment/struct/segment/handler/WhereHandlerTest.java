package cn.davidliu.iv.assignment.struct.segment.handler;

import cn.davidliu.iv.assignment.struct.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.struct.expression.builder.ExpressionNodeBuilder;
import cn.davidliu.iv.assignment.struct.segment.Where;
import org.junit.Assert;
import org.junit.Test;

public class WhereHandlerTest {

    @Test
    public void acquireCurrentQuerySegment() {
        ExpressionNodeBuilder builder = new ExpressionNodeBuilder();
        builder.firstExpression(new ObjectFieldValueFilterOpExpression());
        Where where = new Where(builder.buildResult());
        WhereHandler whereHandler = new WhereHandler(where);
        Assert.assertEquals(whereHandler.acquireCurrentQuerySegment(), where);
    }

    @Test
    public void invokeExecuteSegment() {

    }
}
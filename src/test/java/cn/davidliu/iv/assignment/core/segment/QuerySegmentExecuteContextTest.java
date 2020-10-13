package cn.davidliu.iv.assignment.core.segment;

import org.junit.Assert;
import org.junit.Test;

public class QuerySegmentExecuteContextTest {

    @Test
    public void testToString() {
        String directNewObjectToStringResult = "QuerySegmentExecuteContext{inboundDataSet=null, outboundDataSet=null, groupFuncExecResult=null}";
        Assert.assertEquals(directNewObjectToStringResult, new QuerySegmentExecuteContext().toString());
    }
}
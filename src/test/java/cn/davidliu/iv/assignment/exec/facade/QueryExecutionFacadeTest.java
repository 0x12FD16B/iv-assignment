package cn.davidliu.iv.assignment.exec.facade;

import cn.davidliu.iv.assignment.exec.interfaces.IQueryExecutionFacade;
import org.junit.Assert;
import org.junit.Test;

public class QueryExecutionFacadeTest {

    private final IQueryExecutionFacade queryExecutionFacade = new QueryExecutionFacade();

    @Test
    public void queryShouldReturnNonNullResult() {
        Assert.assertNotNull(queryExecutionFacade.query(null, null, null, null, null));
    }
}
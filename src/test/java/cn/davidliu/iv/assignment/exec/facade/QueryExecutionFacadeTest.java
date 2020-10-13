package cn.davidliu.iv.assignment.exec.facade;

import cn.davidliu.iv.assignment.TestPojo;
import cn.davidliu.iv.assignment.core.expression.*;
import cn.davidliu.iv.assignment.core.expression.builder.ExpressionNodeBuilder;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.core.segment.GroupBy;
import cn.davidliu.iv.assignment.core.segment.Limit;
import cn.davidliu.iv.assignment.core.segment.OrderBy;
import cn.davidliu.iv.assignment.core.segment.Where;
import cn.davidliu.iv.assignment.exec.interfaces.IQueryExecutionFacade;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryExecutionFacadeTest {

    private final IQueryExecutionFacade queryExecutionFacade = new QueryExecutionFacade();

    @Test
    public void testQueryWhenGivenEmptyListThenEmptyListShouldReturn() {
        Assert.assertEquals(queryExecutionFacade.query(Collections.emptyList(), null, null, null, null), Collections.emptyList());
    }

    @Test
    public void testQueryShouldReturnCorrectResult() {
        // 构造 Where
        ExpressionNode whereExpression = new ExpressionNodeBuilder()
                .firstExpression(new ObjectFieldValueFilterOpExpression("intField", Integer.class, ObjectFieldValueFilterOpType.EQ, 1))
                .buildResult();
        Where where = new Where(whereExpression);

        // 构造 GroupBy
        ExpressionNode groupExpressionNode = new ExpressionNodeBuilder()
                .firstExpression(new GroupFieldExpression("intField"))
                .appendExpression(new GroupFuncExecFieldExpression("intField", GroupFuncType.COUNT), ExpressionLogicOpType.NONE)
                .buildResult();
        GroupBy groupBy = new GroupBy(groupExpressionNode);

        // 构造 OrderBy
        ExpressionNode orderByExpression = new ExpressionNodeBuilder().firstExpression(new OrderByFieldExpression("intField", OrderDirection.DESC)).buildResult();
        OrderBy orderBy = new OrderBy(orderByExpression);

        // 构造 Limit
        ExpressionNode limitExpressionNode = new ExpressionNodeBuilder().firstExpression(new LimitExpression(1, 0)).buildResult();
        Limit limit = new Limit(limitExpressionNode);

        // 构建数据集
        List<Object> dataSet = new ArrayList<>();
        TestPojo testPojo = new TestPojo();
        testPojo.setIntField(1);
        dataSet.add(testPojo);

        List<Object> queryResult = queryExecutionFacade.query(dataSet, where, orderBy, groupBy, limit);

        Assert.assertTrue(queryResult != null && queryResult.size() == 1 && queryResult.contains(testPojo));
    }
}
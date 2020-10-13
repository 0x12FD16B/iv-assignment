package cn.davidliu.iv.assignment.core.expression;

import cn.davidliu.iv.assignment.support.exceptions.AssertFailureException;
import org.junit.Assert;
import org.junit.Test;

public class LimitExpressionTest {

    @Test
    public void objectFieldAllowed() {
        LimitExpression limitExpression = new LimitExpression(1, 1);
        Assert.assertFalse(limitExpression.objectFieldAllowed());
    }

    @Test
    public void validateSuccess() {
        LimitExpression limitExpression = new LimitExpression(1, 1);
        limitExpression.validate();
    }

    @Test
    public void validateOffsetFailedShouldThrownException() {
        LimitExpression limitExpression = new LimitExpression(1, -1);
        String exceptionMessage = "Limit 结果集偏移量必须大于等于 0";
        try {
            limitExpression.validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), exceptionMessage);
        }
    }

    @Test
    public void validateLimitFailedShouldThrownException() {
        LimitExpression limitExpression = new LimitExpression(-1, 1);
        String exceptionMessage = "Limit 结果集获取数量必须大于等于 0";
        try {
            limitExpression.validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), exceptionMessage);
        }
    }

    @Test
    public void pojoGetterTest() {
        long limit = 1;
        long offset = 1;
        LimitExpression limitExpression = new LimitExpression(limit, offset);
        Assert.assertEquals(limitExpression.getLimit(), limit);
        Assert.assertEquals(limitExpression.getOffset(), offset);
    }
}
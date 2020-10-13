package cn.davidliu.iv.assignment.core.expression;

import cn.davidliu.iv.assignment.support.exceptions.AssertFailureException;
import org.junit.Assert;
import org.junit.Test;

public class OrderByFieldExpressionTest {

    @Test
    public void testObjectFieldAllowedAlwaysReturnTrue() {
        Assert.assertTrue(new OrderByFieldExpression(null, null).objectFieldAllowed());
    }

    @Test
    public void testValidateWhenObjectFiledNameAssertError() {
        String errMsg = "对象排序字段表达式 [对象字段名称] 不能为空";
        try {
            new OrderByFieldExpression(null, null).validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), errMsg);
        }
    }

    @Test
    public void testValidateWhenDirectionAssertError() {
        String errMsg = "对象排序字段表达式 [排序方向] 不能为空";
        try {
            new OrderByFieldExpression("ddd", null).validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), errMsg);
        }
    }

    @Test
    public void testValidateSuccess() {
        new OrderByFieldExpression("ddd", OrderDirection.ASC).validate();
    }
}
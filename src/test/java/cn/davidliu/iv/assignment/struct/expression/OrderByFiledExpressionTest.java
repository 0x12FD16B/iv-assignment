package cn.davidliu.iv.assignment.struct.expression;

import cn.davidliu.iv.assignment.support.exceptions.AssertFailureException;
import org.junit.Assert;
import org.junit.Test;

public class OrderByFiledExpressionTest {

    @Test
    public void testObjectFieldAllowedAlwaysReturnTrue() {
        Assert.assertTrue(new OrderByFiledExpression(null, null).objectFieldAllowed());
    }

    @Test
    public void testValidateWhenObjectFiledNameAssertError() {
        String errMsg = "对象排序字段表达式 [对象字段名称] 不能为空";
        try {
            new OrderByFiledExpression(null, null).validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), errMsg);
        }
    }

    @Test
    public void testValidateWhenDirectionAssertError() {
        String errMsg = "对象排序字段表达式 [排序方向] 不能为空";
        try {
            new OrderByFiledExpression("ddd", null).validate();
        } catch (AssertFailureException e) {
            Assert.assertEquals(e.getMessage(), errMsg);
        }
    }

    @Test
    public void testValidateSuccess() {
        new OrderByFiledExpression("ddd", OrderDirection.ASC).validate();
    }
}
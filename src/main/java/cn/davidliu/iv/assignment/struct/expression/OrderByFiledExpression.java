package cn.davidliu.iv.assignment.struct.expression;

import cn.davidliu.iv.assignment.support.util.AssertUtil;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 对象排序字段表达式
 *
 * @author David Liu
 */
@Getter
public class OrderByFiledExpression implements Expression {

    /**
     * 排序方向
     */
    private final OrderDirection direction;

    /**
     * 对象字段名称
     */
    private final String objectFieldName;

    public OrderByFiledExpression(String objectFieldName, OrderDirection direction) {
        this.direction = direction;
        this.objectFieldName = objectFieldName;
    }

    @Override
    public boolean objectFieldAllowed() {
        return true;
    }

    @Override
    public void validate() {
        AssertUtil.assertTrue(StringUtils.isNotBlank(objectFieldName), "对象排序字段表达式 [对象字段名称] 不能为空");
        AssertUtil.assertNotNull(direction, "对象排序字段表达式 [排序方向] 不能为空");
    }
}

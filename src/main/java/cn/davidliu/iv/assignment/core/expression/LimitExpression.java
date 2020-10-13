package cn.davidliu.iv.assignment.core.expression;

import cn.davidliu.iv.assignment.support.util.AssertUtil;
import lombok.Getter;

/**
 * Limit 表达式
 *
 * @author David Liu
 */
@Getter
public class LimitExpression implements Expression {

    /**
     * 结果集偏移量
     */
    private final long offset;

    /**
     * 结果集获取数量
     */
    private final long limit;

    public LimitExpression(long limit, long offset) {
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public boolean objectFieldAllowed() {
        return false;
    }

    @Override
    public void validate() {
        AssertUtil.assertTrue(limit >= 0, "Limit 结果集获取数量必须大于等于 0");
        AssertUtil.assertTrue(offset >= 0, "Limit 结果集偏移量必须大于等于 0");
    }
}



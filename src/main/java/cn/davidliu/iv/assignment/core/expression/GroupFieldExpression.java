package cn.davidliu.iv.assignment.core.expression;

import cn.davidliu.iv.assignment.support.util.AssertUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 分组字段表达式
 *
 * @author David Liu
 */
public class GroupFieldExpression implements Expression {

    /**
     * 对象字段名称
     */
    private final String objectFieldName;

    public GroupFieldExpression(String objectFieldName) {
        this.objectFieldName = objectFieldName;
    }

    public String getObjectFieldName() {
        return objectFieldName;
    }

    @Override
    public boolean objectFieldAllowed() {
        return true;
    }

    @Override
    public void validate() {
        AssertUtil.assertTrue(StringUtils.isNotBlank(objectFieldName), "Group By 字段不能为空");
    }
}

package cn.davidliu.iv.assignment.core.expression;

import cn.davidliu.iv.assignment.support.util.AssertUtil;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 分组函数计算字段表达式
 *
 * @author David Liu
 */
@Getter
public class GroupFuncExecFieldExpression implements Expression {

    /**
     * 分组函数类型
     */
    private final GroupFuncType groupFuncType;
    /**
     * 对象字段名称
     */
    private final String objectFieldName;

    public GroupFuncExecFieldExpression(String objectFieldName, GroupFuncType groupFuncType) {
        this.objectFieldName = objectFieldName;
        this.groupFuncType = groupFuncType;
    }

    @Override
    public boolean objectFieldAllowed() {
        return true;
    }

    @Override
    public void validate() {
        AssertUtil.assertTrue(StringUtils.isNotBlank(objectFieldName), "分组函数计算字段表达式 [分组函数类型] 不能为空");
        AssertUtil.assertNotNull(groupFuncType, "分组函数计算字段表达式 [对象字段名称] 不能为空");
    }
}

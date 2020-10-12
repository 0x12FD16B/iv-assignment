package cn.davidliu.iv.assignment.struct.expression;

/**
 * 分组函数计算字段表达式
 *
 * @author David Liu
 */
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

    }
}

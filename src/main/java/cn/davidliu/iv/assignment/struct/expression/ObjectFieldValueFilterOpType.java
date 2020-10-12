package cn.davidliu.iv.assignment.struct.expression;

/**
 * 对象字段查询过滤运算类型
 *
 * @author David Liu
 */
public enum ObjectFieldValueFilterOpType {
    // 值相等
    EQ,
    // 值不等
    NE,
    // 值在集合重
    IN,
    // 值不在集合重
    NOT_IN,
    // 值为空
    IS_NULL,
    // 值不为空
    IS_NOT_NULL
}

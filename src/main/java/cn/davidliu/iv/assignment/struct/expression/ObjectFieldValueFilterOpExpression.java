package cn.davidliu.iv.assignment.struct.expression;

import lombok.Getter;
import lombok.Setter;

/**
 * 对象值字段与传入值逻辑运算表达式
 *
 * @author David Liu
 */
@Getter
@Setter
public class ObjectFieldValueFilterOpExpression implements Expression {

    /**
     * 对象字段名称
     */
    private String objectFieldName;

    /**
     * 对象值类型 class
     */
    private Class<?> objectFieldValueClass;

    /**
     * 过滤运算操作类型
     */
    private ObjectFieldValueFilterOpType opType;

    /**
     * 过滤运算操作数值
     */
    private Object opParticipateValue;

    @Override
    public boolean objectFieldAllowed() {
        return true;
    }

    @Override
    public void validate() {

    }
}

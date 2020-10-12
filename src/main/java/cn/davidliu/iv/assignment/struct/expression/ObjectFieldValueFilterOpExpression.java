package cn.davidliu.iv.assignment.struct.expression;

import cn.davidliu.iv.assignment.support.util.AssertUtil;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * 对象值字段与传入值逻辑运算表达式
 *
 * @author David Liu
 */
@Getter
public class ObjectFieldValueFilterOpExpression implements Expression {

    /**
     * 对象字段名称
     */
    private final String objectFieldName;

    /**
     * 对象值类型 class
     */
    private final Class<?> objectFieldValueClass;

    /**
     * 过滤运算操作类型
     */
    private final ObjectFieldValueFilterOpType opType;

    /**
     * 过滤运算操作数值
     */
    private final Object opParticipateValue;

    public ObjectFieldValueFilterOpExpression(String objectFieldName, Class<?> objectFieldValueClass, ObjectFieldValueFilterOpType opType, Object opParticipateValue) {
        this.objectFieldName = objectFieldName;
        this.objectFieldValueClass = objectFieldValueClass;
        this.opType = opType;
        this.opParticipateValue = opParticipateValue;
    }

    @Override
    public boolean objectFieldAllowed() {
        return true;
    }

    @Override
    public void validate() {
        AssertUtil.assertTrue(StringUtils.isNotBlank(objectFieldName), "对象值字段与传入值逻辑运算表达式中 [对象字段名称] 不能为空");
        AssertUtil.assertNotNull(objectFieldValueClass, "对象值字段与传入值逻辑运算表达式中 [对象值类型 class] 不能为空");
        AssertUtil.assertNotNull(opType, "对象值字段与传入值逻辑运算表达式中 [过滤运算操作类型] 不能为空");

        switch (opType) {
            case EQ:
            case NE:
                AssertUtil.assertNotNull(opParticipateValue, "对象值字段与传入值逻辑运算表达式中 [过滤运算操作类型] 不能为空");
                break;
            case GE:
            case LE:
            case GT:
            case LT:
                AssertUtil.assertNotNull(opParticipateValue, "对象值字段与传入值逻辑运算表达式中 [过滤运算操作数值] 不能为空");
                AssertUtil.assertTrue(opParticipateValue instanceof Comparable, "过滤运算操作数值不可比较");
                break;
            case IN:
            case NOT_IN:
                AssertUtil.assertNotNull(opParticipateValue, "对象值字段与传入值逻辑运算表达式中 [过滤运算操作数值] 不能为空");
                AssertUtil.assertTrue(opParticipateValue instanceof Collection, "过滤运算操作数值类型错误, 应该是集合类型");
                AssertUtil.assertTrue(CollectionUtils.isNotEmpty((Collection<?>) opParticipateValue), "过滤运算操作数值类型错误, 集合不能为空");
                break;
        }
    }
}

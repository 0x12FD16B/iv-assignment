package cn.davidliu.iv.assignment.core.expression.op;

import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import cn.davidliu.iv.assignment.support.log.Log;
import cn.davidliu.iv.assignment.support.log.LogFactory;
import cn.davidliu.iv.assignment.support.util.AssertUtil;
import cn.davidliu.iv.assignment.support.util.ReflectUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对象字段值在集合中运算过滤执行器
 *
 * @author David Liu
 */
public class ObjectFieldValueInFilterOpInvoker implements ObjectFieldValueFilterOpInvoker {

    private final Log log = LogFactory.getLog(ObjectFieldValueInFilterOpInvoker.class);

    @Override
    public List<Object> invokeFilter(List<Object> dataSet, ObjectFieldValueFilterOpExpression expression) {
        AssertUtil.assertNotNull(expression, "对象字段值等于运算过滤执行器执行过滤方法, 运算表达式信息不能为空");
        log.info("开始执行对象字段值在集合当中过滤");
        if (CollectionUtils.isEmpty(dataSet)) {
            log.info("传入数据集为空");
            return Collections.emptyList();
        }
        Class<?> pojoClass = dataSet.get(0).getClass();
        AssertUtil.assertTrue(expression.getOpParticipateValue() instanceof Collection, "运算知类型错误");
        Collection<Object> opParticipateValue = (Collection<Object>) expression.getOpParticipateValue();
        return dataSet.stream().filter(data -> opParticipateValue.contains(ReflectUtil.reflectInvokePojoGetterMethod(pojoClass, data, expression.getObjectFieldName()))).collect(Collectors.toList());
    }

    @Override
    public ObjectFieldValueFilterOpType supportObjectFieldValueFilterOpType() {
        return ObjectFieldValueFilterOpType.IN;
    }
}

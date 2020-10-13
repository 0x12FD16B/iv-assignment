package cn.davidliu.iv.assignment.core.expression.op;

import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import cn.davidliu.iv.assignment.support.log.Log;
import cn.davidliu.iv.assignment.support.log.LogFactory;
import cn.davidliu.iv.assignment.support.util.AssertUtil;
import cn.davidliu.iv.assignment.support.util.ReflectUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 对象字段值等于运算过滤执行器
 *
 * @author David Liu
 */
public class ObjectFieldValueIsNullFilterOpInvoker implements ObjectFieldValueFilterOpInvoker {

    private final Log log = LogFactory.getLog(ObjectFieldValueIsNullFilterOpInvoker.class);

    @Override
    public List<Object> invokeFilter(List<Object> dataSet, ObjectFieldValueFilterOpExpression expression) {
        AssertUtil.assertNotNull(expression, "对象字段值等于运算过滤执行器执行过滤方法, 运算表达式信息不能为空");
        log.info("开始执行对象字段值等于 null 过滤");
        if (CollectionUtils.isEmpty(dataSet)) {
            log.info("传入数据集为空");
            return Collections.emptyList();
        }
        Class<?> pojoClass = dataSet.get(0).getClass();
        return dataSet.stream()
                .filter(data -> Objects.isNull(ReflectUtil.reflectInvokePojoGetterMethod(pojoClass, data, expression.getObjectFieldName())))
                .collect(Collectors.toList());
    }

    @Override
    public ObjectFieldValueFilterOpType supportObjectFieldValueFilterOpType() {
        return ObjectFieldValueFilterOpType.IS_NULL;
    }
}

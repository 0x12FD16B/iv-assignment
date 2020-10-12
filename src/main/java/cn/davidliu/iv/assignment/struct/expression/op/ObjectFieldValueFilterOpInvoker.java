package cn.davidliu.iv.assignment.struct.expression.op;

import cn.davidliu.iv.assignment.struct.expression.ObjectFieldValueFilterOpExpression;

import java.util.List;

/**
 * 对象字段值过滤执行器
 *
 * @author David Liu
 */
public interface ObjectFieldValueFilterOpInvoker {

    /**
     * 执行对象字段值过滤
     *
     * @param dataSet    过滤数据集
     * @param expression 过滤表达式
     * @return 过滤后数据集
     */
    List<Object> invokeFilter(List<Object> dataSet, ObjectFieldValueFilterOpExpression expression);
}

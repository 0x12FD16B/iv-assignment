package cn.davidliu.iv.assignment.core.expression.op.handler;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;

import java.util.List;

/**
 * 过滤运算结果连接处理器
 *
 * @author David Liu
 */
public interface FilterOpResultLogicJoinHandler {

    /**
     * 执行结果集连接
     *
     * @param left  左运算结果集
     * @param right 右运算结果集
     * @return 连接结果
     */
    List<Object> invokeJoin(List<Object> left, List<Object> right);

    /**
     * 返回支持的逻辑运算符类型
     *
     * @return 表达式逻辑操作类型
     */
    ExpressionLogicOpType supportExpressionLogicOpType();
}

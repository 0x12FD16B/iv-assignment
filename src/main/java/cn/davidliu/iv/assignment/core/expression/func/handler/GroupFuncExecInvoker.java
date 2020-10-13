package cn.davidliu.iv.assignment.core.expression.func.handler;

import cn.davidliu.iv.assignment.core.expression.GroupFuncType;

import java.util.List;

/**
 * 分组函数执行器
 *
 * @author David Liu
 */
public interface GroupFuncExecInvoker {

    /**
     * 获取支持的分组函数类型
     *
     * @return 支持的分组函数类型
     */
    GroupFuncType supportGroupFuncType();

    /**
     * 执行分组函数
     *
     * @param groupDataSet 分组函数
     * @return 执行结果
     */
    List<Object> invokeGroupFunc(List<List<Object>> groupDataSet);
}

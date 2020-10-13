package cn.davidliu.iv.assignment.exec.interfaces;


import cn.davidliu.iv.assignment.core.segment.GroupBy;
import cn.davidliu.iv.assignment.core.segment.Limit;
import cn.davidliu.iv.assignment.core.segment.OrderBy;
import cn.davidliu.iv.assignment.core.segment.Where;

import java.util.List;

/**
 * 查询执行 Facade
 *
 * @author David Liu
 */
public interface IQueryExecutionFacade {

    /**
     * 查询方法
     *
     * @param dataSet 查询数据集
     * @param where   where 过滤条件
     * @param orderBy 排序条件
     * @param groupBy 分组条件
     * @param limit   最大返回结果数
     * @return 结果集
     */
    List<Object> query(List<Object> dataSet, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit);
}

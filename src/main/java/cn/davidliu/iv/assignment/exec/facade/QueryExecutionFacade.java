package cn.davidliu.iv.assignment.exec.facade;

import cn.davidliu.iv.assignment.exec.interfaces.IQueryExecutionFacade;
import cn.davidliu.iv.assignment.struct.segment.GroupBy;
import cn.davidliu.iv.assignment.struct.segment.Limit;
import cn.davidliu.iv.assignment.struct.segment.OrderBy;
import cn.davidliu.iv.assignment.struct.segment.Where;

import java.util.Collections;
import java.util.List;

/**
 * 查询执行 Facade
 *
 * @author David Liu
 */
public class QueryExecutionFacade implements IQueryExecutionFacade {
    @Override
    public List<Object> query(List<Object> dataSet, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit) {
        return Collections.emptyList();
    }
}

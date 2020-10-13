package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.core.QuerySegmentType;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;

/**
 * 查询语句段处理器
 *
 * @author David Liu
 */
public interface QuerySegmentHandler {

    /**
     * 执行语句段查询
     *
     * @param executeContext 查询语句段执行上下文参数
     */
    void executeSegment(QuerySegmentExecuteContext executeContext);

    /**
     * 获取支持的查询段类型
     *
     * @return 支持的查询段类型
     */
    QuerySegmentType supportQuerySegmentType();
}

package cn.davidliu.iv.assignment.core.segment.handler.chain;

import java.util.List;

/**
 * 查询语句段执行器链接口定义
 *
 * @author David Liu
 */
public interface IQuerySegmentHandlerChain {

    /**
     * 执行查询
     *
     * @param inboundDataSet 查询结果集
     * @return 查询结果
     */
    List<Object> invokeQuery(List<Object> inboundDataSet);

}

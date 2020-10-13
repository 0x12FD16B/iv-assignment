package cn.davidliu.iv.assignment.core.segment.handler.chain;

import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.core.segment.handler.QuerySegmentHandler;
import cn.davidliu.iv.assignment.support.util.AssertUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询语句段执行器链
 *
 * @author David Liu
 */
public final class QuerySegmentHandlerChain implements IQuerySegmentHandlerChain {

    private final List<QuerySegmentHandler> segmentHandlers;

    private QuerySegmentHandlerChain(List<QuerySegmentHandler> segmentHandlers) {
        this.segmentHandlers = segmentHandlers;
    }

    public static QuerySegmentHandlerChain make(QuerySegmentHandler... handlers) {
        AssertUtil.assertTrue(handlers != null, "生成查询语句段执行链参数错误");
        List<QuerySegmentHandler> handlerList = new ArrayList<>();
        for (QuerySegmentHandler handler : handlers) {
            if (handler != null) {
                handlerList.add(handler);
            }
        }
        return new QuerySegmentHandlerChain(handlerList);
    }

    public List<Object> invokeQuery(List<Object> inboundDataSet) {
        QuerySegmentExecuteContext context = new QuerySegmentExecuteContext();
        context.setInboundDataSet(inboundDataSet);

        if (CollectionUtils.isEmpty(segmentHandlers)) {
            return inboundDataSet;
        }

        List<Object> result = null;
        for (QuerySegmentHandler segmentHandler : segmentHandlers) {
            segmentHandler.executeSegment(context);
            context.setInboundDataSet(context.getOutboundDataSet());
            result = context.getOutboundDataSet();
            context.setOutboundDataSet(null);
        }
        return result;
    }
}

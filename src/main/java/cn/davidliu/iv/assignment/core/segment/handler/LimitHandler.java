package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.core.expression.LimitExpression;
import cn.davidliu.iv.assignment.core.segment.Limit;
import cn.davidliu.iv.assignment.core.segment.QuerySegment;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.support.log.Log;
import cn.davidliu.iv.assignment.support.log.LogFactory;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Limit 查询语句段处理器
 *
 * @author David Liu
 */
public class LimitHandler extends AbstractQuerySegmentHandlerInvokeTemplate {

    private final Log log = LogFactory.getLog(LimitHandler.class);

    /**
     * Limit 语句段
     */
    private final Limit limit;

    public LimitHandler(Limit limit) {
        this.limit = limit;
    }

    @Override
    public QuerySegment acquireCurrentQuerySegment() {
        return limit;
    }

    @Override
    protected void invokeExecuteSegment(QuerySegmentExecuteContext executeContext) {
        log.info("执行 Limit 语句段查询");
        List<Object> inboundDataSet = executeContext.getInboundDataSet();
        LimitExpression limitExpression = (LimitExpression) limit.segmentExpression().getCurrentNode();
        List<Object> outboundDataSet;
        if (CollectionUtils.isEmpty(inboundDataSet) || inboundDataSet.size() < limitExpression.getOffset()) {
            outboundDataSet = Collections.emptyList();
        } else {
            long recvCount = limitExpression.getLimit();
            outboundDataSet = new ArrayList<>();
            for (int i = 0; i < inboundDataSet.size() && recvCount > 0; i++) {
                if (i < limitExpression.getOffset()) {
                    continue;
                }
                outboundDataSet.add(inboundDataSet.get(i));
                recvCount--;
            }
        }

        executeContext.setOutboundDataSet(outboundDataSet);
    }
}

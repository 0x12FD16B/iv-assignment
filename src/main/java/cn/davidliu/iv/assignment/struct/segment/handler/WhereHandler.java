package cn.davidliu.iv.assignment.struct.segment.handler;

import cn.davidliu.iv.assignment.struct.segment.QuerySegment;
import cn.davidliu.iv.assignment.struct.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.struct.segment.Where;

/**
 * Where 语句段处理器
 *
 * @author David Liu
 */
public class WhereHandler extends AbstractQuerySegmentHandlerInvokeTemplate {

    /**
     * where 语句段
     */
    private final Where where;

    public WhereHandler(Where where) {
        this.where = where;
    }

    @Override
    protected QuerySegment acquireCurrentQuerySegment() {
        return where;
    }

    @Override
    protected void invokeExecuteSegment(QuerySegmentExecuteContext executeContext) {

    }
}

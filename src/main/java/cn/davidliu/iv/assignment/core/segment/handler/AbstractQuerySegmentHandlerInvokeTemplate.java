package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.core.QuerySegmentType;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.core.segment.QuerySegment;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;

/**
 * 查询语句段处理器执行模板方法
 *
 * @author David Liu
 */
public abstract class AbstractQuerySegmentHandlerInvokeTemplate implements QuerySegmentHandler {

    /**
     * 获取查询语句段
     *
     * @return 查询语句段表达式
     */
    protected abstract QuerySegment acquireCurrentQuerySegment();

    /**
     * 执行查询语句段
     *
     * @param executeContext 查询语句段执行上下文参数
     */
    protected abstract void invokeExecuteSegment(QuerySegmentExecuteContext executeContext);

    @Override
    public QuerySegmentType supportQuerySegmentType() {
        return acquireCurrentQuerySegment().getSegmentType();
    }

    @Override
    public void executeSegment(QuerySegmentExecuteContext executeContext) {
        this.validateSegment();
        this.invokeExecuteSegment(executeContext);
    }

    private void validateSegment() {
        QuerySegment segment = acquireCurrentQuerySegment();
        segment.validate();
        ExpressionNode t = segment.segmentExpression();
        while (t != null && t.getCurrentNode() != null) {
            t.validate();
            t.getCurrentNode().validate();
            t = t.getNextNode();
        }
    }
}

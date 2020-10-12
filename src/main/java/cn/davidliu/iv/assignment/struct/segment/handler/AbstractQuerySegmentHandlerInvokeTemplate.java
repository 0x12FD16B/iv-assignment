package cn.davidliu.iv.assignment.struct.segment.handler;

import cn.davidliu.iv.assignment.struct.expression.ExpressionNode;
import cn.davidliu.iv.assignment.struct.segment.QuerySegment;
import cn.davidliu.iv.assignment.struct.segment.QuerySegmentExecuteContext;

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
    public void executeSegment(QuerySegmentExecuteContext executeContext) {
        this.validateSegment();
        this.invokeExecuteSegment(executeContext);
    }

    private void validateSegment() {
        QuerySegment segment = acquireCurrentQuerySegment();
        segment.validate();
        ExpressionNode t = segment.segmentExpression();
        while (t != null && t.getCurrentNode() != null) {
            t.getCurrentNode().validate();
            t = t.getNextNode();
        }
    }
}

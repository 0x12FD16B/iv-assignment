package cn.davidliu.iv.assignment.core.segment.handler;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpExpression;
import cn.davidliu.iv.assignment.core.expression.op.singleton.FilterOpResultLogicJoinHandlerContainer;
import cn.davidliu.iv.assignment.core.expression.op.singleton.ObjectFieldValueFilterOpInvokerContainer;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.core.expression.support.ObjectFieldValueFilterOpResultNode;
import cn.davidliu.iv.assignment.core.segment.QuerySegment;
import cn.davidliu.iv.assignment.core.segment.QuerySegmentExecuteContext;
import cn.davidliu.iv.assignment.core.segment.Where;
import cn.davidliu.iv.assignment.support.exceptions.AsyncTaskExecutionException;
import cn.davidliu.iv.assignment.support.log.Log;
import cn.davidliu.iv.assignment.support.log.LogFactory;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Where 语句段处理器
 *
 * @author David Liu
 */
public class WhereHandler extends AbstractQuerySegmentHandlerInvokeTemplate {

    private final Log log = LogFactory.getLog(WhereHandler.class);

    /**
     * where 语句段
     */
    private final Where where;

    /**
     * where 语句段表达式执行线程池
     */
    private final ThreadPoolExecutor whereExpressionExecuteThreadExecutor;

    public WhereHandler(Where where, ThreadPoolExecutor whereExpressionExecuteThreadExecutor) {
        this.where = where;
        this.whereExpressionExecuteThreadExecutor = whereExpressionExecuteThreadExecutor;
    }

    @Override
    public QuerySegment acquireCurrentQuerySegment() {
        return where;
    }

    @Override
    protected void invokeExecuteSegment(QuerySegmentExecuteContext executeContext) {
        log.info("执行 Where 查询");
        if (whereExpressionExecuteThreadExecutor == null) {
            this.invokeExecuteSegmentSerial(executeContext);
        } else {
            this.invokeExecuteSegmentParallel(executeContext);
        }
    }

    private void invokeExecuteSegmentSerial(QuerySegmentExecuteContext executeContext) {
        List<Object> outboundDataSet;
        if (CollectionUtils.isEmpty(executeContext.getInboundDataSet())) {
            outboundDataSet = Collections.emptyList();
        } else {
            ExpressionNode node = where.segmentExpression();

            List<Object> inboundDataSet = executeContext.getInboundDataSet();
            ObjectFieldValueFilterOpInvokerContainer opInvokerContainer = ObjectFieldValueFilterOpInvokerContainer.getInstance();
            FilterOpResultLogicJoinHandlerContainer joinHandlerContainer = FilterOpResultLogicJoinHandlerContainer.getInstance();

            List<Object> prevResult = Collections.emptyList();
            ExpressionLogicOpType prevLogicOpType = ExpressionLogicOpType.OR;

            List<Object> tempResult = null;

            while (node != null) {
                ObjectFieldValueFilterOpExpression expression = (ObjectFieldValueFilterOpExpression) node.getCurrentNode();
                List<Object> currentResult = opInvokerContainer.acquireOpInvoker(expression.getOpType()).invokeFilter(inboundDataSet, expression);
                // 和上一个结果合并
                tempResult = joinHandlerContainer.acquireOpInvoker(prevLogicOpType).invokeJoin(prevResult, currentResult);
                prevLogicOpType = node.getLogicOpType();
                prevResult = tempResult;
                node = node.getNextNode();
            }

            outboundDataSet = tempResult;
        }

        executeContext.setOutboundDataSet(outboundDataSet);

    }

    private void invokeExecuteSegmentParallel(QuerySegmentExecuteContext executeContext) {
        List<Object> outboundDataSet;
        if (CollectionUtils.isEmpty(executeContext.getInboundDataSet())) {
            outboundDataSet = Collections.emptyList();
        } else {
            ExpressionNode node = where.segmentExpression();
            Map<ExpressionNode, Future<List<Object>>> futureTaskMap = new HashMap<>();
            List<Object> inboundDataSet = executeContext.getInboundDataSet();
            ObjectFieldValueFilterOpInvokerContainer opInvokerContainer = ObjectFieldValueFilterOpInvokerContainer.getInstance();
            while (node != null) {
                ObjectFieldValueFilterOpExpression expression = (ObjectFieldValueFilterOpExpression) node.getCurrentNode();
                Callable<List<Object>> callable = () -> opInvokerContainer.acquireOpInvoker(expression.getOpType()).invokeFilter(inboundDataSet, expression);
                futureTaskMap.put(node, whereExpressionExecuteThreadExecutor.submit(callable));
                node = node.getNextNode();
            }

            node = where.segmentExpression();

            ObjectFieldValueFilterOpResultNode resultNodeHead = new ObjectFieldValueFilterOpResultNode(null);
            ObjectFieldValueFilterOpResultNode tempNode = resultNodeHead;
            while (node != null) {
                Future<List<Object>> future = futureTaskMap.get(node);
                try {
                    List<Object> result = future.get();
                    ObjectFieldValueFilterOpResultNode currentResultNode = new ObjectFieldValueFilterOpResultNode(result);
                    currentResultNode.setConcatLogicOpType(node.getLogicOpType());
                    tempNode.setNext(currentResultNode);
                    tempNode = tempNode.getNext();
                } catch (InterruptedException | ExecutionException e) {
                    log.error("执行过滤表达式逻辑出错", e);
                    throw new AsyncTaskExecutionException(e);
                }
                node = node.getNextNode();
            }

            FilterOpResultLogicJoinHandlerContainer joinHandlerContainer = FilterOpResultLogicJoinHandlerContainer.getInstance();

            List<Object> prevResult = Collections.emptyList();
            ExpressionLogicOpType prevLogicOpType = ExpressionLogicOpType.OR;

            while (resultNodeHead.getNext() != null) {
                ObjectFieldValueFilterOpResultNode resultNode = resultNodeHead.getNext();
                if (prevLogicOpType != null) {
                    prevResult = joinHandlerContainer.acquireOpInvoker(prevLogicOpType).invokeJoin(prevResult, resultNode.getCurrentResult());
                    prevLogicOpType = resultNode.getConcatLogicOpType();
                }
                resultNodeHead = resultNode;
            }

            outboundDataSet = prevResult;
        }

        executeContext.setOutboundDataSet(outboundDataSet);
    }
}

package cn.davidliu.iv.assignment.struct.expression.builder;

import cn.davidliu.iv.assignment.struct.expression.Expression;
import cn.davidliu.iv.assignment.struct.expression.ExpressionLogicOpType;
import cn.davidliu.iv.assignment.struct.expression.ExpressionNode;
import cn.davidliu.iv.assignment.support.util.AssertUtil;

/**
 * 表达式链表构造器
 *
 * @author David Liu
 */
public final class ExpressionNodeBuilder {

    /**
     * 表达式链表
     */
    private final ExpressionNode expressionNode;
    /**
     * 表达式第一个节点是否初始化
     */
    private boolean firstNodeInitialized;

    public ExpressionNodeBuilder() {
        this.expressionNode = new ExpressionNode(null);
        firstNodeInitialized = false;
    }

    /**
     * 初始化表达式链表第一个表达式
     *
     * @param expression 表达式
     * @return 表达式链表构造器
     */
    public ExpressionNodeBuilder firstExpression(Expression expression) {
        AssertUtil.assertNotNull(expression, "传入表达式不能为空");
        AssertUtil.assertFalse(firstNodeInitialized, "表达式链表第一个节点已经初始化");
        this.expressionNode.setCurrentNode(expression);
        firstNodeInitialized = true;
        return this;
    }

    /**
     * 追加表达式到表达式链表尾部
     *
     * @param expression 表达式
     * @return 表达式链表构造器
     */
    public ExpressionNodeBuilder appendExpression(Expression expression, ExpressionLogicOpType opType) {
        AssertUtil.assertNotNull(expression, "传入表达式不能为空");
        AssertUtil.assertNotNull(opType, "传入表达式逻辑连接符不能为空");
        AssertUtil.assertTrue(firstNodeInitialized, "表达式链表第一个节点未初始化");
        this.expressionNode.setLogicOpType(opType);
        this.expressionNode.setHasNext(true);
        this.expressionNode.setNextNode(new ExpressionNode(expression));
        return this;
    }

    /**
     * 获取构建结果
     *
     * @return 表达式链表构建结果
     */
    public ExpressionNode buildResult() {
        AssertUtil.assertTrue(firstNodeInitialized, "表达式链表第一个节点未初始化");
        return expressionNode;
    }
}

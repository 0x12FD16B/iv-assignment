package cn.davidliu.iv.assignment.struct.expression;

import cn.davidliu.iv.assignment.struct.interfaces.IQueryPhraseValidation;
import cn.davidliu.iv.assignment.support.util.AssertUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 表达式链表节点
 *
 * @author David Liu
 */
@Getter
@Setter
public class ExpressionNode implements IQueryPhraseValidation {
    /**
     * 表达式当前节点
     */
    private Expression currentNode;
    /**
     * 表达式链表是否有下一个节点
     */
    private boolean hasNext;
    /**
     * 表达式链表下一个节点
     */
    private ExpressionNode nextNode;
    /**
     * 表达式之间的逻辑连接符
     */
    private ExpressionLogicOpType logicOpType;

    public ExpressionNode(Expression currentNode, boolean hasNext, ExpressionNode nextNode, ExpressionLogicOpType logicOpType) {
        this.currentNode = currentNode;
        this.hasNext = hasNext;
        this.nextNode = nextNode;
        this.logicOpType = logicOpType;
    }

    public ExpressionNode(Expression currentNode) {
        this.currentNode = currentNode;
    }

    @Override
    public void validate() {
        AssertUtil.assertNotNull(this.currentNode, "当前表达式节点不能为空");
        if (hasNext) {
            AssertUtil.assertNotNull(nextNode, "当 hasNext 为 true 时, 下一个节点不能为空");
            AssertUtil.assertNotNull(logicOpType, "当下一节点不为空时, 表达式之间的逻辑连接符不能为空");
        }
    }
}

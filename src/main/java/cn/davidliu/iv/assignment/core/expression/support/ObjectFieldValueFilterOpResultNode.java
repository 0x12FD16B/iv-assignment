package cn.davidliu.iv.assignment.core.expression.support;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import lombok.Getter;

import java.util.List;

/**
 * 对象字段值等于运算过滤运算结果链表
 *
 * @author David Liu
 */
@Getter
public class ObjectFieldValueFilterOpResultNode {

    /**
     * 当前链表节点运算结果
     */
    private final List<Object> currentResult;

    /**
     * 下一个节点
     */
    private ObjectFieldValueFilterOpResultNode next;

    /**
     * 连接下一个节点的逻辑运算符
     */
    private ExpressionLogicOpType concatLogicOpType;

    public ObjectFieldValueFilterOpResultNode(List<Object> currentResult) {
        this.currentResult = currentResult;
    }

    public void setNext(ObjectFieldValueFilterOpResultNode next) {
        this.next = next;
    }

    public void setConcatLogicOpType(ExpressionLogicOpType concatLogicOpType) {
        this.concatLogicOpType = concatLogicOpType;
    }
}

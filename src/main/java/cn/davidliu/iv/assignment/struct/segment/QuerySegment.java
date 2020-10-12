package cn.davidliu.iv.assignment.struct.segment;

import cn.davidliu.iv.assignment.struct.QuerySegmentType;
import cn.davidliu.iv.assignment.struct.expression.ExpressionNode;
import cn.davidliu.iv.assignment.struct.interfaces.IQueryPhraseValidation;

/**
 * 查询语句段顶层接口
 *
 * @author David Liu
 */
public interface QuerySegment extends IQueryPhraseValidation {

    /**
     * 获取查询语句段类型
     *
     * @return 查询语句段类型
     */
    QuerySegmentType getSegmentType();

    /**
     * 获取语句段表达式链表
     *
     * @return 语句段表达式链表
     */
    ExpressionNode segmentExpression();
}

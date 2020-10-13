package cn.davidliu.iv.assignment.core.segment;

import cn.davidliu.iv.assignment.core.QuerySegmentType;
import cn.davidliu.iv.assignment.core.expression.support.ExpressionNode;
import cn.davidliu.iv.assignment.support.util.AssertUtil;

/**
 * Where 查询子句结构
 *
 * @author David Liu
 */
public class Where implements QuerySegment {

    /**
     * 查询语句表达式
     */
    private final ExpressionNode segmentExpression;

    /**
     * 查询语句段类型
     */
    private final QuerySegmentType segmentType = QuerySegmentType.WHERE;

    public Where(ExpressionNode segmentExpression) {
        this.segmentExpression = segmentExpression;
    }

    @Override
    public QuerySegmentType getSegmentType() {
        return segmentType;
    }

    @Override
    public ExpressionNode segmentExpression() {
        return segmentExpression;
    }

    @Override
    public void validate() {
        AssertUtil.assertNotNull(segmentExpression, "Where 表达式信息不能为空");
    }
}

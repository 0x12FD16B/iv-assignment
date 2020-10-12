package cn.davidliu.iv.assignment.struct.segment;

import cn.davidliu.iv.assignment.struct.QuerySegmentType;
import cn.davidliu.iv.assignment.struct.expression.ExpressionNode;
import cn.davidliu.iv.assignment.support.util.AssertUtil;

/**
 * Limit 结果限制子句
 *
 * @author David Liu
 */
public class Limit implements QuerySegment {

    /**
     * 查询语句表达式
     */
    private final ExpressionNode segmentExpression;

    /**
     * 查询语句段类型
     */
    private final QuerySegmentType segmentType = QuerySegmentType.LIMIT;

    public Limit(ExpressionNode segmentExpression) {
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
        AssertUtil.assertNotNull(segmentExpression, "Limit 表达式信息不能为空");
        AssertUtil.assertNull(segmentExpression.getNextNode(), "Limit 只能有一个表达式信息");
    }
}

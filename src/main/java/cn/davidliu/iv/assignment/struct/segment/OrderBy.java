package cn.davidliu.iv.assignment.struct.segment;

import cn.davidliu.iv.assignment.struct.QuerySegmentType;
import cn.davidliu.iv.assignment.struct.expression.ExpressionNode;
import cn.davidliu.iv.assignment.support.util.AssertUtil;

/**
 * Order By 排序子句
 *
 * @author David Liu
 */
public class OrderBy implements QuerySegment {

    /**
     * 查询语句表达式
     */
    private final ExpressionNode segmentExpression;

    /**
     * 查询语句段类型
     */
    private final QuerySegmentType segmentType = QuerySegmentType.ORDER_BY;

    public OrderBy(ExpressionNode segmentExpression) {
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
        AssertUtil.assertNotNull(segmentExpression, "Order By 表达式信息不能为空");
    }
}

package cn.davidliu.iv.assignment.struct.segment.builder;

import cn.davidliu.iv.assignment.struct.segment.QuerySegment;
import cn.davidliu.iv.assignment.struct.segment.QuerySegmentNode;

/**
 * 查询语句段构造器
 *
 * @author David Liu
 */
public final class QuerySegmentNodeBuilder {
    /**
     * 查询语句段
     */
    private QuerySegmentNode segmentNode;

    /**
     * 获取构建结果
     *
     * @return 构建结果
     */
    public QuerySegmentNode buildResult() {
        return segmentNode;
    }

    /**
     * 添加查询语句段
     *
     * @param querySegment 查询语句段
     * @return 查询语句段构造器
     */
    public QuerySegmentNodeBuilder appendSegment(QuerySegment querySegment) {
        if (segmentNode == null) {
            segmentNode = new QuerySegmentNode(querySegment);
        } else {
            segmentNode.setNext(new QuerySegmentNode(querySegment));
        }
        return this;
    }
}

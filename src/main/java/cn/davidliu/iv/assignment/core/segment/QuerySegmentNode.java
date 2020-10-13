package cn.davidliu.iv.assignment.core.segment;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询语段节点
 *
 * @author David Liu
 */
@Getter
@Setter
public class QuerySegmentNode {

    /**
     * 当前查询语句
     */
    private QuerySegment segment;

    /**
     * 下一个查询语段节点
     */
    private QuerySegmentNode next;

    public QuerySegmentNode(QuerySegment segment) {
        this.segment = segment;
    }
}

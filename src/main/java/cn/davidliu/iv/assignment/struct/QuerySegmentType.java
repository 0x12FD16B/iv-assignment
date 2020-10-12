package cn.davidliu.iv.assignment.struct;

/**
 * 查询短语段类型
 *
 * @author David Liu
 */
public enum QuerySegmentType {
    // WHERE 语句
    WHERE,
    // GROUP_BY
    GROUP_BY,
    // ORDER_BY
    ORDER_BY,
    // LIMIT
    LIMIT;
}

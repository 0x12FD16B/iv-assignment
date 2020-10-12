package cn.davidliu.iv.assignment.struct.segment;

import lombok.Data;

import java.util.List;

/**
 * 查询语句段执行上下文参数
 *
 * @author David Liu
 */
@Data
public class QuerySegmentExecuteContext {

    /**
     * 执行语句段结果集
     */
    private List<Object> inboundDataSet;

    /**
     * 语句段执行完成后结果集
     */
    private List<Object> outboundDataSet;
}

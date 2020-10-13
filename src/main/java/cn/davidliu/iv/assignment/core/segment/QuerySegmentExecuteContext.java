package cn.davidliu.iv.assignment.core.segment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 查询语句段执行上下文参数
 *
 * @author David Liu
 */
@Getter
@Setter
public class QuerySegmentExecuteContext {

    /**
     * 执行语句段结果集
     */
    private List<Object> inboundDataSet;

    /**
     * 语句段执行完成后结果集
     */
    private List<Object> outboundDataSet;

    /**
     * 分组函数执行结果
     */
    private List<List<Object>> groupFuncExecResult;

    @Override
    public String toString() {
        return "QuerySegmentExecuteContext{" +
                "inboundDataSet=" + inboundDataSet +
                ", outboundDataSet=" + outboundDataSet +
                ", groupFuncExecResult=" + groupFuncExecResult +
                '}';
    }
}

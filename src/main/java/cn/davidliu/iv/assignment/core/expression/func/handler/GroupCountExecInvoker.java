package cn.davidliu.iv.assignment.core.expression.func.handler;

import cn.davidliu.iv.assignment.core.expression.GroupFuncType;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分组求和函数计算执行器
 *
 * @author David Liu
 */
public class GroupCountExecInvoker implements GroupFuncExecInvoker {
    @Override
    public GroupFuncType supportGroupFuncType() {
        return GroupFuncType.COUNT;
    }

    @Override
    public List<Object> invokeGroupFunc(List<List<Object>> groupDataSet) {
        if (CollectionUtils.isEmpty(groupDataSet)) {
            return Collections.emptyList();
        }

        List<Object> result = new ArrayList<>();
        for (List<Object> ds : groupDataSet) {
            result.add(ds.size());
        }
        return result;
    }
}

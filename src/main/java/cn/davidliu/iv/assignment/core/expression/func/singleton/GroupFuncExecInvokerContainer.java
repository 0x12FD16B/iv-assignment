package cn.davidliu.iv.assignment.core.expression.func.singleton;

import cn.davidliu.iv.assignment.core.expression.GroupFuncType;
import cn.davidliu.iv.assignment.core.expression.func.handler.GroupCountExecInvoker;
import cn.davidliu.iv.assignment.core.expression.func.handler.GroupFuncExecInvoker;
import cn.davidliu.iv.assignment.support.util.AssertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 分组函数计算处理器容器
 *
 * @author David Liu
 */
public class GroupFuncExecInvokerContainer {
    private static volatile GroupFuncExecInvokerContainer instance = null;

    /**
     * 分组函数计算处理器容器 Map
     */
    private final Map<GroupFuncType, GroupFuncExecInvoker> funcTypeMappedInvokerMap;

    private GroupFuncExecInvokerContainer() {
        funcTypeMappedInvokerMap = new HashMap<>();
        // 注册分组函数计算处理器
        GroupCountExecInvoker groupCountExecInvoker = new GroupCountExecInvoker();
        funcTypeMappedInvokerMap.put(groupCountExecInvoker.supportGroupFuncType(), groupCountExecInvoker);

    }

    /**
     * 获取单例对象
     *
     * @return 单例对象
     */
    public static GroupFuncExecInvokerContainer getInstance() {
        if (instance == null) {
            synchronized (GroupFuncExecInvokerContainer.class) {
                if (instance == null) {
                    instance = new GroupFuncExecInvokerContainer();
                }
            }
        }
        return instance;
    }

    /**
     * 获取过滤运算结果连接处理器
     *
     * @param op 表达式逻辑操作类型
     * @return 过滤运算结果连接处理器
     */
    public GroupFuncExecInvoker acquireFuncExecInvoker(GroupFuncType op) {
        AssertUtil.assertTrue(funcTypeMappedInvokerMap.containsKey(op), "表达式逻辑操作类型不支持");
        return funcTypeMappedInvokerMap.get(op);
    }
}

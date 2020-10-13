package cn.davidliu.iv.assignment.core.expression.op.singleton;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import cn.davidliu.iv.assignment.core.expression.op.handler.FilterOpResultAndJoinHandler;
import cn.davidliu.iv.assignment.core.expression.op.handler.FilterOpResultLogicJoinHandler;
import cn.davidliu.iv.assignment.core.expression.op.handler.FilterOpResultOrJoinHandler;
import cn.davidliu.iv.assignment.support.util.AssertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 过滤运算结果连接处理器容器
 *
 * @author David Liu
 */
public class FilterOpResultLogicJoinHandlerContainer {
    private static volatile FilterOpResultLogicJoinHandlerContainer instance = null;

    /**
     * 过滤运算结果连接处理器容器 Map
     */
    private final Map<ExpressionLogicOpType, FilterOpResultLogicJoinHandler> opTypeMappedHandlerMap;

    private FilterOpResultLogicJoinHandlerContainer() {
        opTypeMappedHandlerMap = new HashMap<>();
        // 注册对象字段值过滤执行器
        FilterOpResultAndJoinHandler andJoinHandler = new FilterOpResultAndJoinHandler();
        opTypeMappedHandlerMap.put(andJoinHandler.supportExpressionLogicOpType(), andJoinHandler);
        FilterOpResultOrJoinHandler orJoinHandler = new FilterOpResultOrJoinHandler();
        opTypeMappedHandlerMap.put(orJoinHandler.supportExpressionLogicOpType(), orJoinHandler);

    }

    /**
     * 获取单例对象
     *
     * @return 单例对象
     */
    public static FilterOpResultLogicJoinHandlerContainer getInstance() {
        if (instance == null) {
            synchronized (FilterOpResultLogicJoinHandlerContainer.class) {
                if (instance == null) {
                    instance = new FilterOpResultLogicJoinHandlerContainer();
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
    public FilterOpResultLogicJoinHandler acquireOpInvoker(ExpressionLogicOpType op) {
        AssertUtil.assertTrue(opTypeMappedHandlerMap.containsKey(op), "表达式逻辑操作类型不支持");
        return opTypeMappedHandlerMap.get(op);
    }
}

package cn.davidliu.iv.assignment.core.expression.op.singleton;

import cn.davidliu.iv.assignment.core.expression.ObjectFieldValueFilterOpType;
import cn.davidliu.iv.assignment.core.expression.op.*;
import cn.davidliu.iv.assignment.support.util.AssertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象字段值过滤执行器容器
 *
 * @author David Liu
 */
public final class ObjectFieldValueFilterOpInvokerContainer {
    private static volatile ObjectFieldValueFilterOpInvokerContainer instance = null;

    /**
     * 对象字段值过滤执行器容器 Map
     */
    private final Map<ObjectFieldValueFilterOpType, ObjectFieldValueFilterOpInvoker> opTypeMappedInvokerMap;

    private ObjectFieldValueFilterOpInvokerContainer() {
        opTypeMappedInvokerMap = new HashMap<>();
        // 注册对象字段值过滤执行器
        ObjectFieldValueEqFilterOpInvoker eqFilterOpInvoker = new ObjectFieldValueEqFilterOpInvoker();
        opTypeMappedInvokerMap.put(eqFilterOpInvoker.supportObjectFieldValueFilterOpType(), eqFilterOpInvoker);
        ObjectFieldValueNeFilterOpInvoker neFilterOpInvoker = new ObjectFieldValueNeFilterOpInvoker();
        opTypeMappedInvokerMap.put(neFilterOpInvoker.supportObjectFieldValueFilterOpType(), neFilterOpInvoker);
        ObjectFieldValueInFilterOpInvoker inFilterOpInvoker = new ObjectFieldValueInFilterOpInvoker();
        opTypeMappedInvokerMap.put(inFilterOpInvoker.supportObjectFieldValueFilterOpType(), inFilterOpInvoker);
        ObjectFieldValueIsNullFilterOpInvoker isNullFilterOpInvoker = new ObjectFieldValueIsNullFilterOpInvoker();
        opTypeMappedInvokerMap.put(isNullFilterOpInvoker.supportObjectFieldValueFilterOpType(), isNullFilterOpInvoker);
        ObjectFieldValueGeFilterOpInvoker geFilterOpInvoker = new ObjectFieldValueGeFilterOpInvoker();
        opTypeMappedInvokerMap.put(geFilterOpInvoker.supportObjectFieldValueFilterOpType(), geFilterOpInvoker);
    }

    /**
     * 获取单例对象
     *
     * @return 单例对象
     */
    public static ObjectFieldValueFilterOpInvokerContainer getInstance() {
        if (instance == null) {
            synchronized (ObjectFieldValueFilterOpInvokerContainer.class) {
                if (instance == null) {
                    instance = new ObjectFieldValueFilterOpInvokerContainer();
                }
            }
        }
        return instance;
    }

    /**
     * 获取对象字段值过滤执行器
     *
     * @param op 对象字段查询过滤运算类型
     * @return 对象字段值过滤执行器
     */
    public ObjectFieldValueFilterOpInvoker acquireOpInvoker(ObjectFieldValueFilterOpType op) {
        AssertUtil.assertTrue(opTypeMappedInvokerMap.containsKey(op), "对象字段值过滤执行器不支持");
        return opTypeMappedInvokerMap.get(op);
    }
}

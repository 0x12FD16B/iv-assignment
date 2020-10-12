package cn.davidliu.iv.assignment.support.util;

import cn.davidliu.iv.assignment.support.exceptions.ReflectGetPojoFieldValueException;
import cn.davidliu.iv.assignment.support.log.Log;
import cn.davidliu.iv.assignment.support.log.LogFactory;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射工具类
 *
 * @author David Liu
 */
public final class ReflectUtil {

    private static final Log log = LogFactory.getLog(ReflectUtil.class);

    /**
     * 反射调用 Pojo 字段 Getter 方法
     *
     * @param pojoClass Pojo Class
     * @param fieldName 字段名称
     * @return Pojo 字段值
     */
    public static Object reflectInvokePojoGetterMethod(Class<?> pojoClass, Object pojo, String fieldName) {
        String methodName = "get" + StringUtils.capitalize(fieldName);
        try {
            return pojoClass.getMethod(methodName).invoke(pojo);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("反射调用 Pojo 字段 Getter 方法出错", e);
            throw new ReflectGetPojoFieldValueException("反射调用 Pojo 字段 Getter 方法出错");
        }
    }
}

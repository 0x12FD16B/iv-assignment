package cn.davidliu.iv.assignment.core.expression;

import cn.davidliu.iv.assignment.core.interfaces.IQueryPhraseValidation;

/**
 * 参数表达式
 *
 * @author David Liu
 */
public interface Expression extends IQueryPhraseValidation {

    /**
     * 是否传递对象字段
     *
     * @return boolean, true: 是, false: 否
     */
    boolean objectFieldAllowed();
}

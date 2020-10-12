package cn.davidliu.iv.assignment.struct.expression;

/**
 * 对象排序字段表达式
 *
 * @author David Liu
 */
public class OrderByFiledExpression implements Expression {

    /**
     * 排序方向
     */
    private final OrderDirection direction;

    /**
     * 对象字段名称
     */
    private final String objectFieldName;

    public OrderByFiledExpression(String objectFieldName, OrderDirection direction) {
        this.direction = direction;
        this.objectFieldName = objectFieldName;
    }

    @Override
    public boolean objectFieldAllowed() {
        return true;
    }

    @Override
    public void validate() {

    }

    public OrderDirection getDirection() {
        return direction;
    }

    public String getObjectFieldName() {
        return objectFieldName;
    }
}

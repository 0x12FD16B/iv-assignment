package cn.davidliu.iv.assignment.core.expression.support;

import cn.davidliu.iv.assignment.core.expression.ExpressionLogicOpType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class ObjectFieldValueFilterOpResultNodeTest {

    @Test
    public void generalTest() {
        ObjectFieldValueFilterOpResultNode resultNode = new ObjectFieldValueFilterOpResultNode(Collections.emptyList());
        Assert.assertEquals(resultNode.getCurrentResult(), Collections.emptyList());
        Assert.assertNull(resultNode.getNext());
        Assert.assertNull(resultNode.getConcatLogicOpType());
        ObjectFieldValueFilterOpResultNode nextNode = new ObjectFieldValueFilterOpResultNode(Collections.emptyList());
        resultNode.setNext(nextNode);
        Assert.assertEquals(resultNode.getNext(), nextNode);
        resultNode.setConcatLogicOpType(ExpressionLogicOpType.AND);
        Assert.assertEquals(resultNode.getConcatLogicOpType(), ExpressionLogicOpType.AND);
    }
}
package com.sample.calculator.parser.node;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The MultiplicatonNode performs a mathematical multiplication on two expression nodes.
 */
public class MultiplicationNode extends AbstractBinaryNode {
    /**
     * Initialize a new MultiplicationNode.
     *
     * @param firstNode  The first child node.
     * @param otherNode The other child node.
     */
    public MultiplicationNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        super(firstNode, otherNode);
    }

    @Override
    public BigDecimal evaluate() {
        ExpressionNode firstNode = getLeftNode();
        ExpressionNode otherNode = getRightNode();

        return firstNode.evaluate().multiply(otherNode.evaluate(), MathContext.DECIMAL32);
    }
}

package com.sample.calculator.parser.node;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The DivisionNode performs a mathematical division on two expression nodes.
 */
public class DivisionNode extends AbstractBinaryNode {
    /**
     * Initialize a new DivisionNode.
     *
     * @param firstNode  The first child node.
     * @param otherNode The other child node.
     */
    public DivisionNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        super(firstNode, otherNode);
    }

    @Override
    public BigDecimal evaluate() {
        ExpressionNode firstNode = getLeftNode();
        ExpressionNode otherNode = getRightNode();

        return firstNode.evaluate().divide(otherNode.evaluate(), MathContext.DECIMAL32);
    }
}

package com.sample.calculator.parser.node;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The SubtractionNode performs a mathematical subtraction on two expression nodes.
 */
public class SubtractionNode extends AbstractBinaryNode {
    /**
     * Initialize a new SubtractionNode.
     *
     * @param firstNode  The first child node.
     * @param otherNode The other child node.
     */
    public SubtractionNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        super(firstNode, otherNode);
    }

    @Override
    public BigDecimal evaluate() {
        ExpressionNode firstNode = getLeftNode();
        ExpressionNode otherNode = getRightNode();

        return firstNode.evaluate().subtract(otherNode.evaluate(), MathContext.DECIMAL32);
    }
}

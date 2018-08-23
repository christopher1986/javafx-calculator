package com.sample.calculator.parser.node;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The AdditionNode performs a mathematical addition on two expression nodes.
 */
public class AdditionNode extends AbstractBinaryNode {
    /**
     * Initialize a new AdditionNode.
     *
     * @param firstNode  The first child node.
     * @param otherNode The other child node.
     */
    public AdditionNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        super(firstNode, otherNode);
    }

    @Override
    public BigDecimal evaluate() {
        ExpressionNode firstNode = getLeftNode();
        ExpressionNode otherNode = getRightNode();

        return firstNode.evaluate().add(otherNode.evaluate(), MathContext.DECIMAL32);
    }
}

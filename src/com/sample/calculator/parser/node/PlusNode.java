package com.sample.calculator.parser.node;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The PlusNode performs a unary positive on another expression node.
 */
public class PlusNode implements ExpressionNode {
    /**
     * The node associated with this operator.
     */
    private ExpressionNode node;

    /**
     * Initialize a new PlusNode.
     *
     * @param node The node associated with this operator.
     */
    public PlusNode(ExpressionNode node) {
        this.node = node;
    }

    @Override
    public BigDecimal evaluate() {
        return node.evaluate().abs(MathContext.DECIMAL32);
    }
}

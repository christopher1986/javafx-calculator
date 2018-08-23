package com.sample.calculator.parser.node;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The MinusNode performs a unary negation on another expression node.
 */
public class MinusNode implements ExpressionNode {
    /**
     * The node associated with this operator.
     */
    private ExpressionNode node;

    /**
     * Initialize a new MinusNode.
     *
     * @param node The node associated with this operator.
     */
    public MinusNode(ExpressionNode node) {
        this.node = node;
    }

    @Override
    public BigDecimal evaluate() {
        return node.evaluate().negate(MathContext.DECIMAL32);
    }
}

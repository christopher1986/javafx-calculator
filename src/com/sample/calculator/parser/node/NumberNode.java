package com.sample.calculator.parser.node;

import java.math.BigDecimal;

/**
 * The NumberNode can represent both real and fractional numbers.
 */
public class NumberNode implements ExpressionNode {
    /**
     * The numeric value this node represents.
     */
    private double value;

    /**
     * Initialize a new NumberNode.
     *
     * @param value The numeric value.
     */
    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate() {
        return new BigDecimal(value);
    }
}

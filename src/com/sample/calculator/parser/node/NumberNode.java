package com.sample.calculator.parser.node;

import java.math.BigDecimal;

/**
 * The NumberNode can represent both real and fractional numbers.
 */
public class NumberNode implements ExpressionNode {
    /**
     * The numeric value this node represents.
     */
    private Double value;

    /**
     * Initialize a new NumberNode.
     *
     * @param value The numeric value.
     */
    public NumberNode(Double value) {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate() {
        if (value % 1 == 0) {
            return BigDecimal.valueOf(value.longValue());
        }

        return BigDecimal.valueOf(value);
    }
}

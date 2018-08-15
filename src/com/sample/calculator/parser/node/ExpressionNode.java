package com.sample.calculator.parser.node;

import java.math.BigDecimal;

/**
 * The ExpressionNode is a data structure holding a value and belongs to some larger tree like structure.
 */
public interface ExpressionNode {
    /**
     * Evaluate the expression.
     *
     * @return The result of evaluating the expression.
     */
    BigDecimal evaluate();
}

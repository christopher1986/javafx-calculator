package com.sample.calculator.parser.operator;

/**
 * The operator is an operation that requires one or more elements (called operands) to produce another element.
 */
public interface Operator {
    /**
     * Returns the symbol associated with this operator.
     *
     * @return The character symbol
     */
    String getSymbol();

    /**
     * Returns the precedence of this operator.
     *
     * @return The operator precedence.
     */
    int getPrecedence();
}

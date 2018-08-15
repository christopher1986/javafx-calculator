package com.sample.calculator.parser.operator.binary;

import com.sample.calculator.parser.operator.BinaryOperator;
import com.sample.calculator.parser.operator.Operator;

/**
 * The AbstractBinaryOperator provides a skeleton implementation for binary operators.
 */
public abstract class AbstractBinaryOperator implements BinaryOperator {
    /**
     * Indicates operations are grouped from the left.
     */
    public static final int LEFT_ASSOCIATIVE = 0x01;

    /**
     * Indicates operations are grouped from the right.
     */
    public static final int RIGHT_ASSOCIATIVE = 0x02;

    /**
     * Returns the associativity of this operator.
     *
     * The associativity property determines how operators of the same precedence
     * are grouped in the absence of parentheses.
     *
     * @return The associativity of this operator.
     */
    abstract protected int getAssociativity();

    @Override
    public boolean isRightAssociative() {
        return getAssociativity() == RIGHT_ASSOCIATIVE;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getSymbol().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BinaryOperator)) {
            return false;
        }

        Operator operator = (Operator) o;

        return getSymbol().equals(operator.getSymbol());
    }
}

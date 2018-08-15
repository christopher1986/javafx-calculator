package com.sample.calculator.parser.operator.unary;

import com.sample.calculator.parser.operator.Operator;
import com.sample.calculator.parser.operator.UnaryOperator;

/**
 * The AbstractUnaryOperator provides a skeleton implementation for binary operators.
 */
abstract public class AbstractUnaryOperator implements UnaryOperator
{
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getSymbol().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UnaryOperator)) {
            return false;
        }

        Operator operator = (Operator) o;

        return getSymbol().equals(operator.getSymbol());
    }
}

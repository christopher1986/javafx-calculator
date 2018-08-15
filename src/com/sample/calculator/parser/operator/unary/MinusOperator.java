package com.sample.calculator.parser.operator.unary;

import com.sample.calculator.parser.node.ExpressionNode;
import com.sample.calculator.parser.node.MinusNode;

/**
 * The MinusOperator is a unary operator that performs a unary negation on a number.
 */
public class MinusOperator extends AbstractUnaryOperator
{
    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public int getPrecedence() {
        return 501;
    }

    @Override
    public ExpressionNode createUnaryNode(ExpressionNode node) {
        return new MinusNode(node);
    }
}

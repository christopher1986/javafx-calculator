package com.sample.calculator.parser.operator.unary;

import com.sample.calculator.parser.node.ExpressionNode;
import com.sample.calculator.parser.node.PlusNode;

/**
 * The PlusOperator is a unary operator that performs a unary positive on a number.
 */
public class PlusOperator extends AbstractUnaryOperator
{
    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public int getPrecedence() {
        return 501;
    }

    @Override
    public ExpressionNode createUnaryNode(ExpressionNode node) {
        return new PlusNode(node);
    }
}

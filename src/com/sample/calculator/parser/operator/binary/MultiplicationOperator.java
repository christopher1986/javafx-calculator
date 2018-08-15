package com.sample.calculator.parser.operator.binary;

import com.sample.calculator.parser.node.ExpressionNode;
import com.sample.calculator.parser.node.MultiplicationNode;

/**
 * The MultiplicationOperator is a binary operator for performing a mathematical multiplication on two operands.
 */
public class MultiplicationOperator extends AbstractBinaryOperator {
    @Override
    protected int getAssociativity() {
        return LEFT_ASSOCIATIVE;
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public int getPrecedence() {
        return 40;
    }

    @Override
    public ExpressionNode createBinaryNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        return new MultiplicationNode(firstNode, otherNode);
    }
}

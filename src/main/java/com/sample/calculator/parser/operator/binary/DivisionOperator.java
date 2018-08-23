package com.sample.calculator.parser.operator.binary;

import com.sample.calculator.parser.node.DivisionNode;
import com.sample.calculator.parser.node.ExpressionNode;

/**
 * The DivisionOperator is a binary operator for performing a mathematical division on two operands.
 */
public class DivisionOperator extends AbstractBinaryOperator {
    @Override
    protected int getAssociativity() {
        return LEFT_ASSOCIATIVE;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public int getPrecedence() {
        return 40;
    }

    @Override
    public ExpressionNode createBinaryNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        return new DivisionNode(firstNode, otherNode);
    }
}

package com.sample.calculator.parser.operator.binary;

import com.sample.calculator.parser.node.ExpressionNode;
import com.sample.calculator.parser.node.SubtractionNode;

/**
 * The SubtractionOperator is a binary operator for performing a mathematical subtraction on two operands.
 */
public class SubtractionOperator extends AbstractBinaryOperator {
    @Override
    protected int getAssociativity() {
        return LEFT_ASSOCIATIVE;
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public int getPrecedence() {
        return 30;
    }

    @Override
    public ExpressionNode createBinaryNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        return new SubtractionNode(firstNode, otherNode);
    }
}

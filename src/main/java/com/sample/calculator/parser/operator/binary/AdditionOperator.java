package com.sample.calculator.parser.operator.binary;

import com.sample.calculator.parser.node.AdditionNode;
import com.sample.calculator.parser.node.ExpressionNode;

/**
 * The AdditionOperator is a binary operator for performing a mathematical addition on two operands.
 */
public class AdditionOperator extends AbstractBinaryOperator {
    @Override
    protected int getAssociativity() {
        return LEFT_ASSOCIATIVE;
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public int getPrecedence() {
        return 30;
    }

    @Override
    public ExpressionNode createBinaryNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        return new AdditionNode(firstNode, otherNode);
    }
}

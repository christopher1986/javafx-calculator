package com.sample.calculator.parser.operator;

import com.sample.calculator.parser.node.ExpressionNode;

/**
 * The BinaryOperator is an operation that requires two elements (called operands) to produce another element.
 */
public interface BinaryOperator extends Operator {
    /**
     * Returns true if this operator is right associative.
     *
     * @return True if operator is right associative.
     */
    boolean isRightAssociative();

    /**
     * Create an ExpressionNode for this operator with the specified children.
     *
     * @param firstNode the first child node for the binary node.
     * @param otherNode the other child node for the binary node.
     * @return A newly created ExpressionNode with the specified children.
     */
    ExpressionNode createBinaryNode(ExpressionNode firstNode, ExpressionNode otherNode);
}

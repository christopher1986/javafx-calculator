package com.sample.calculator.parser.operator;

import com.sample.calculator.parser.node.ExpressionNode;

/**
 * The BinaryOperator is an operation that requires one element (called an operand) to produce another element.
 */
public interface UnaryOperator extends Operator {
    /**
     * Create an ExpressionNode for this operator with the specified child.
     *
     * @param node the child node for the unary node.
     * @return A newly created ExpressionNode with the specified child.
     */
    ExpressionNode createUnaryNode(ExpressionNode node);
}

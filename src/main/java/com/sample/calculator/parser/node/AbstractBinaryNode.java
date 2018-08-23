package com.sample.calculator.parser.node;

/**
 * The AbstractBinaryNode implements a skeleton implementation of the BinaryOperator interface.
 */
abstract class AbstractBinaryNode implements ExpressionNode {
    /**
     * The left node associated with this operator.
     */
    private ExpressionNode leftNode;

    /**
     * The right node associated with this operator.
     */
    private ExpressionNode rightNode;

    /**
     * Initialize a new AbstractBinaryNode.
     *
     * @param firstNode  The left node associated with this operator.
     * @param otherNode The right node associated with this operator.
     */
    AbstractBinaryNode(ExpressionNode firstNode, ExpressionNode otherNode) {
        this.leftNode = firstNode;
        this.rightNode = otherNode;
    }

    /**
     * Returns the left node of this binary node.
     *
     * @return The left child node.
     */
    ExpressionNode getLeftNode()
    {
        return leftNode;
    }

    /**
     * Returns the right child of this binary node.
     *
     * @return The right child node.
     */
    ExpressionNode getRightNode()
    {
        return rightNode;
    }
}

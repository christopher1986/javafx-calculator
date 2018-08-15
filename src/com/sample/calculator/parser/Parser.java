package com.sample.calculator.parser;

import com.sample.calculator.parser.node.ExpressionNode;

/**
 * The Parser creates a tree structure by analyzing a string of symbols.
 */
public interface Parser {
    /**
     * Parse the specified expression into an abstract syntax tree.
     *
     * @param expression The expression to parse.
     * @return The tree created by parsing the specified expression.
     */
    ExpressionNode parse(String expression);
}

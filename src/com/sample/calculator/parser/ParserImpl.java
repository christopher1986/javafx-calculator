package com.sample.calculator.parser;

import com.sample.calculator.parser.lexer.Token;
import com.sample.calculator.parser.lexer.TokenStream;
import com.sample.calculator.parser.node.ExpressionNode;
import com.sample.calculator.parser.node.MultiplicationNode;
import com.sample.calculator.parser.node.NumberNode;
import com.sample.calculator.parser.operator.BinaryOperator;
import com.sample.calculator.parser.operator.Operator;
import com.sample.calculator.parser.operator.UnaryOperator;

import java.util.*;

/**
 * The ParserImpl creates a tree structure by analyzing a string of symbols.
 */
public class ParserImpl implements Parser {
    /**
     * A mapping between symbols and binary operators.
     */
    private Map<String, BinaryOperator> binaryOperators;

    /**
     * A mapping between symbols and unary operators.
     */
    private Map<String, UnaryOperator> unaryOperators;

    /**
     * Initialize a new ParserImpl.
     *
     * @param binaryOperators The binary operators.
     * @param unaryOperators The unary operators.
     */
    public ParserImpl(Set<BinaryOperator> binaryOperators, Set<UnaryOperator> unaryOperators) {
        this.binaryOperators = createOperatorMap(binaryOperators);
        this.unaryOperators = createOperatorMap(unaryOperators);
    }

    @Override
    public ExpressionNode parse(String expression) {
        Lexer lexer = new LexerImpl(this.getOperators());
        TokenStream<Token> tokens = lexer.tokenize(expression);

        return parseExpression(tokens);
    }

    /**
     * Parse tokens into an expression node using the precedence climbing algorithm.
     *
     * The convention within algebra denotes that multiplication should be used
     * when symbols are put side by side; which is also known as a juxtaposition.
     *
     * @param tokens The tokens to parse.
     * @return The expression node that was parsed.
     * @link http://www.madmath.com/2013/10/are-parentheses-multiplication.html
     */
    private ExpressionNode parseExpression(TokenStream<Token> tokens) {
        Deque<ExpressionNode> nodes = new ArrayDeque<>();

        while (!tokens.isEmpty()) {
            ExpressionNode node = parseExpression(tokens, 0);

            if (!nodes.isEmpty()) {
                node = new MultiplicationNode(nodes.pop(), node);
            }

            nodes.push(node);
        }

        return nodes.pop();
    }

    /**
     * Parse tokens into an expression node using the precedence climbing algorithm.
     *
     * @param tokens The tokens to parse.
     * @param precedence the precedence of the previous operator.
     * @return The expression node that was parsed.
     */
    private ExpressionNode parseExpression(TokenStream<Token> tokens, int precedence) {
        ExpressionNode node = parsePrimary(tokens);

        while (!tokens.isEmpty() && isBinaryOperator(tokens.current())) {
            BinaryOperator operator = getBinaryOperator(tokens.current());

            if (operator.getPrecedence() < precedence) {
                break;
            }

            tokens.next();

            int newPrecedence = (operator.isRightAssociative())
                    ? operator.getPrecedence()
                    : operator.getPrecedence() + 1;

            node = operator.createBinaryNode(node, parseExpression(tokens, newPrecedence));
        }


        return node;
    }

    /**
     * Parse a single node from the specified token stream.
     *
     * @param tokens The tokens to parse.
     * @return The node that was parsed.
     */
    private ExpressionNode parsePrimary(TokenStream<Token> tokens) {
        Token.Type parenthesis = Token.Type.PARENTHESIS;
        Token.Type number = Token.Type.NUMBER;

        if (!tokens.isEmpty() && isUnaryOperator(tokens.current())) {
            UnaryOperator operator = getUnaryOperator(tokens.next());
            ExpressionNode node = parseExpression(tokens, operator.getPrecedence());

            return operator.createUnaryNode(node);
        } else if (tokens.matches(subject -> subject.equalsType(parenthesis) && subject.equalsValue("("))) {
            tokens.next();
            ExpressionNode node = parseExpression(tokens, 0);

            tokens.expects(subject -> subject.equalsType(parenthesis) && subject.equalsValue(")"));

            return node;
        }

        Token token = tokens.expects(subject -> subject.equalsType(number));

        return new NumberNode(Double.parseDouble(token.getValue()));
    }

    /**
     * Returns true if the specified token represents a binary operator.
     *
     * @param token The token that will be tested.
     * @return True if the tokens represent a binary operator.
     */
    private boolean isBinaryOperator(Token token) {
        return binaryOperators.containsKey(token.getValue());
    }

    /**
     * Returns if present the binary operator associated with the specified token.
     *
     * @param token The token for which to obtain a binary operator.
     * @return The binary operator that was found, or null on failure.
     */
    private BinaryOperator getBinaryOperator(Token token) {
        return binaryOperators.get(token.getValue());
    }

    /**
     * Returns true if the specified token represents a unary operator.
     *
     * @param token The token that will be tested.
     * @return True if the tokens represent a unary operator.
     */
    private boolean isUnaryOperator(Token token) {
        return unaryOperators.containsKey(token.getValue());
    }

    /**
     * Returns if present the unary operator associated with the specified token.
     *
     * @param token The token for which to obtain a unary operator.
     * @return The unary operator that was found, or null on failure.
     */
    private UnaryOperator getUnaryOperator(Token token) {
        return unaryOperators.get(token.getValue());
    }

    /**
     * Returns a collection of operators contained by this parser.
     *
     * @return A collection of operators.
     */
    private Collection<Operator> getOperators() {
        int capacity = binaryOperators.size() + unaryOperators.size();

        List<Operator> operators = new ArrayList<>(capacity);
        operators.addAll(binaryOperators.values());
        operators.addAll(unaryOperators.values());

        return operators;
    }

    /**
     * Creates a mapping between operators and associated symbols.
     *
     * @param operators The operators for which to create the mapping.
     * @return A mapping between symbols and operators.
     */
    private <T extends Operator> Map<String, T> createOperatorMap(Collection<T> operators) {
        Map<String, T> mapping = new HashMap<>();

        for (T operator : operators) {
            mapping.put(operator.getSymbol(), operator);
        }

        return mapping;
    }
}

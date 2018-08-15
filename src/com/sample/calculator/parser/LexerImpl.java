package com.sample.calculator.parser;

import com.sample.calculator.parser.exception.SyntaxException;
import com.sample.calculator.parser.lexer.InputSequence;
import com.sample.calculator.parser.lexer.Token;
import com.sample.calculator.parser.lexer.TokenStream;
import com.sample.calculator.parser.lexer.TokenStreamImpl;
import com.sample.calculator.parser.lexer.command.NumberCommand;
import com.sample.calculator.parser.lexer.command.OperatorCommand;
import com.sample.calculator.parser.lexer.command.ParenthesisCommand;
import com.sample.calculator.parser.lexer.command.TokenizeCommand;
import com.sample.calculator.parser.operator.*;
import com.sample.calculator.parser.util.BracketBalancer;
import com.sample.calculator.parser.util.Pair;

import java.util.*;

/**
 * The LexerImpl creates tokens for lexemes (strings of symbols) that were found in an expression.
 */
public class LexerImpl implements Lexer {
    /**
     * A collection of binary operators.
     */
    private Collection<Operator> operators;

    /**
     * A collection of bracket pairs.
     */
    private Set<Pair<String, String>> brackets = new HashSet<Pair<String, String>>() {{
        add(new Pair<>("(", ")"));
    }};

    /**
     * Initialize a new LexerImpl.
     *
     * @param operators A collection of binary operators.
     */
    LexerImpl(Collection<Operator> operators) {
        this.operators = operators;
    }

    @Override
    public TokenStream<Token> tokenize(String expression) {
        BracketBalancer balancer = new BracketBalancer(brackets);
        TokenStream<Token> tokens = executeCommands(new InputSequence(expression), balancer);

        if (!balancer.isBalanced()) {
            throw new SyntaxException("Malformed expression: missing a closing bracket.");
        }

        return tokens;
    }

    /**
     * Tokenize the specified input by executing tokenize commands.
     *
     * @param input The input the commands will tokenize.
     * @param balancer The balancer to ensure brackets are properly closed.
     * @return A collection to which tokens have been added.
     */
    private TokenStream<Token> executeCommands(InputSequence input, BracketBalancer balancer) {
        TokenStream<Token> tokens = new TokenStreamImpl<>();
        List<TokenizeCommand> commands = getCommands(tokens, balancer);

        main:
        while (!input.isEmpty()) {
            for (TokenizeCommand command : commands) {
                if (command.execute(input)) {
                    continue main;
                }
            }

            throw new SyntaxException("Malformed expression: found an illegal symbol.");
        }

        return tokens;
    }

    /**
     * Returns a collection commands with which to tokenize the input.
     *
     * @param tokens The stream to which tokens will be added.
     * @param balancer The balancer to ensure brackets are properly closed.
     * @return A collection of commands with which to tokenize the input.
     */
    private List<TokenizeCommand> getCommands(TokenStream<Token> tokens, BracketBalancer balancer) {
        TokenizeCommand[] commands = new TokenizeCommand[] {
            new NumberCommand(tokens),
            new ParenthesisCommand(tokens, balancer, brackets),
            new OperatorCommand(tokens, operators)
        };

        return new ArrayList<>(Arrays.asList(commands));
    }
}

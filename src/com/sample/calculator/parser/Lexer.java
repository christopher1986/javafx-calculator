package com.sample.calculator.parser;

import com.sample.calculator.parser.lexer.Token;
import com.sample.calculator.parser.lexer.TokenStream;

/**
 * The Lexer creates tokens for lexemes (strings of symbols) that were found in an expression.
 */
public interface Lexer {
    /**
     * Tokenize the specified expression.
     *
     * @param expression The expression to tokenize.
     * @return A stream of tokens.
     */
    TokenStream<Token> tokenize(String expression);
}

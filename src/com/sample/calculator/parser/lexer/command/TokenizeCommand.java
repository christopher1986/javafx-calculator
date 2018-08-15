package com.sample.calculator.parser.lexer.command;

import com.sample.calculator.parser.lexer.InputSequence;

/**
 * The TokenizeCommand is responsible for creating tokens from an input sequence.
 */
public interface TokenizeCommand {
    /**
     * Create a token from the specified input sequence.
     *
     * @param input The input sequence to tokenize.
     * @return True if a token was created, or false on failure.
     */
    boolean execute(InputSequence input);
}

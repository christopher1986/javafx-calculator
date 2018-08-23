package com.sample.calculator.parser.exception;

/**
 * Thrown to indicate that the calculator has encountered a syntax error.
 */
public class SyntaxException extends IllegalArgumentException
{
    /**
     * Initialize a new SyntaxException
     *
     * @param message the detail message.
     */
    public SyntaxException(String message) {
        super(message);
    }
}

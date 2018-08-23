package com.sample.calculator.parser.lexer.exception;


/**
 * Thrown by methods in TokenStream class to indicate the stream is empty.
 */
public class EmptyStreamException extends RuntimeException
{
    /**
     * Initialize a new EmptyStreamException
     *
     * @param message the detail message.
     */
    public EmptyStreamException(String message) {
        super(message);
    }
}

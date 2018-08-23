package com.sample.calculator.parser.lexer;

/**
 * The Token class that holds information which was found during the lexical analysis.
 */
public interface Token {
    /**
     * The Type enum contains valid token types.
     */
    enum Type { NUMBER, OPERATOR, PARENTHESIS }

    /**
     * Returns true if the token type equals the specified type.
     *
     * @param type The type that will be tested.
     * @return True if the token type equals the specified type.
     */
    boolean equalsType(Type type);

    /**
     * Returns true if the token value equals the specified value.
     *
     * @param value The value that will be tested.
     * @return True if the token value equals the specified value.
     */
    boolean equalsValue(String value);

    /**
     * Returns the token type.
     *
     * @return The type that identifies this token.
     */
    Type getType();

    /**
     * Returns the token value.
     *
     * @return The value this token holds.
     */
    String getValue();
}

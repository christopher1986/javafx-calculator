package com.sample.calculator.parser.lexer;

/**
 * The TokenImpl is an immutable object that holds information that was found during the lexical analysis.
 */
public class TokenImpl implements Token {
    /**
     * The token type.
     */
    private Type type;

    /**
     * The token value.
     */
    private String value;

    /**
     * Initialize a new TokenImpl.
     *
     * @param type The type that identifies this token.
     * @param value The value this token will hold.
     */
    public TokenImpl(Type type, String value)
    {
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equalsType(Type type) {
        return this.type.equals(type);
    }

    @Override
    public boolean equalsValue(String value)
    {
        return this.value.equals(value);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}

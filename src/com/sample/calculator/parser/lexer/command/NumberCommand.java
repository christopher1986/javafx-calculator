package com.sample.calculator.parser.lexer.command;

import com.sample.calculator.parser.lexer.InputSequence;
import com.sample.calculator.parser.lexer.Token;
import com.sample.calculator.parser.lexer.TokenImpl;
import com.sample.calculator.parser.lexer.TokenStream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The NumberCommand creates tokens for real numbers which are obtained from an input sequence.
 */
public class NumberCommand implements TokenizeCommand {
    /**
     * A regular expression with which to match real numbers.
     */
    private final Pattern pattern = Pattern.compile("^[0-9]+(\\.[0-9]+)?");

    /**
     * A collection to which tokens will be added.
     */
    private TokenStream<Token> tokens;

    /**
     * Intialize a new NumberCommand.
     *
     * @param tokens A collection to which tokens will be added.
     */
    public NumberCommand(TokenStream<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public boolean execute(InputSequence input) {
        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.lookingAt();

        if (matches) {
            String value = input.substring(matcher.end());
            input.advance(matcher.end());

            tokens.add(new TokenImpl(Token.Type.NUMBER, value));
        }

        return matches;
    }
}

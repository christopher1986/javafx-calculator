package com.sample.calculator.parser.lexer.command;

import com.sample.calculator.parser.lexer.InputSequence;
import com.sample.calculator.parser.lexer.Token;
import com.sample.calculator.parser.lexer.TokenImpl;
import com.sample.calculator.parser.lexer.TokenStream;
import com.sample.calculator.parser.util.BracketBalancer;
import com.sample.calculator.parser.util.Pair;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The NumberCommand creates tokens for parentheses which are obtained from an input sequence.
 */
public class ParenthesisCommand implements TokenizeCommand {
    /**
     * A regular expression with which to match parentheses.
     */
    private final Pattern pattern;

    /**
     * A balancer to test whether brackets are balanced.
     */
    private BracketBalancer balancer;

    /**
     * A collection to which tokens will be added.
     */
    private TokenStream<Token> tokens;

    /**
     * Intialize a new NumberCommand.
     *
     * @param tokens The collection to which tokens will be added.
     * @param balancer The balancer to ensure brackets are properly closed.
     * @param brackets A collection of bracket to match.
     */
    public ParenthesisCommand(
            TokenStream<Token> tokens,
            BracketBalancer balancer,
            Collection<Pair<String, String>> brackets
    ) {
        this.pattern = createParenthesisPattern(brackets);
        this.tokens = tokens;
        this.balancer = balancer;
    }

    @Override
    public boolean execute(InputSequence input) {
        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.lookingAt();

        if (matches) {
            String value = input.substring(matcher.end());
            input.advance(matcher.end());

            tokens.add(new TokenImpl(Token.Type.PARENTHESIS, value));
            balancer.consume(value);
        }

        return matches;
    }

    /**
     * Returns a regex pattern with which to match parentheses.
     *
     * @param pairs A collection of bracket pairs.
     * @return A regex pattern for the specified parentheses.
     */
    private Pattern createParenthesisPattern(Collection<Pair<String, String>> pairs)
    {
        StringBuilder builder = new StringBuilder();

        for (Pair<String, String> pair : pairs) {
            builder.append(pair.getKey());
            builder.append(pair.getValue());
        }

        String regex = String.format("^[%s]", Pattern.quote(builder.toString()));

        return Pattern.compile(regex);
    }
}

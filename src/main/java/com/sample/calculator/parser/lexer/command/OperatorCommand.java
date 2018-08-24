package com.sample.calculator.parser.lexer.command;

import com.sample.calculator.parser.lexer.InputSequence;
import com.sample.calculator.parser.lexer.Token;
import com.sample.calculator.parser.lexer.TokenImpl;
import com.sample.calculator.parser.lexer.TokenStream;
import com.sample.calculator.parser.operator.Operator;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The OperatorCommand creates tokens for binary operators which are obtained from an input sequence.
 */
public class OperatorCommand implements TokenizeCommand {
    /**
     * A regular expression with which to match operators.
     */
    private final Pattern pattern;

    /**
     * A collection to which tokens will be added.
     */
    private TokenStream<Token> tokens;

    /**
     * Initialize a new NumberCommand.
     *
     * @param tokens The collection to which tokens will be added.
     * @param operators A collection of binary operators to match.
     */
    public OperatorCommand(TokenStream<Token> tokens, Collection<Operator> operators) {
        this.pattern = createOperatorPattern(operators);
        this.tokens = tokens;
    }

    @Override
    public boolean execute(InputSequence input) {
        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.lookingAt();

        if (matches) {
            String value = input.substring(matcher.end());
            input.advance(matcher.end());

            tokens.add(new TokenImpl(Token.Type.OPERATOR, value));
        }

        return matches;
    }

    /**
     * Returns a regex pattern with which to match operators.
     *
     * @param operators A collection of binary operators.
     * @return A regex pattern for the specified operators.
     */
    private Pattern createOperatorPattern(Collection<Operator> operators)
    {
        StringBuilder builder = new StringBuilder();

        for (Operator operator : operators) {
            builder.append(operator.getSymbol());
        }

        String regex = String.format("^[%s]", Pattern.quote(builder.toString()));

        return Pattern.compile(regex);
    }
}

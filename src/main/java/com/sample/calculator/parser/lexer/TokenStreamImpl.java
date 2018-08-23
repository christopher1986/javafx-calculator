package com.sample.calculator.parser.lexer;

import com.sample.calculator.parser.exception.SyntaxException;
import com.sample.calculator.parser.lexer.exception.EmptyStreamException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * The TokenStream maintains a collection of Token objects.
 */
public class TokenStreamImpl<E extends Token> implements TokenStream<E> {
    /**
     * The list of tokens.
     */
    private List<E> tokens = new ArrayList<>();

    /**
     * Index of the current token.
     */
    private int index = 0;

    @Override
    public void add(E token) {
        tokens.add(token);
    }

    @Override
    public void addAll(Collection<E> tokens) {
        this.tokens.addAll(tokens);
    }

    @Override
    public E next() throws EmptyStreamException {
        if (isEmpty()) {
            throw new EmptyStreamException("Unable to obtain next token from empty stream");
        }

        return tokens.get(index++);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean matches(Predicate<E> predicate) {
        return predicate.test(current());
    }

    @Override
    public E expects(Predicate<E> predicate) throws SyntaxException {
        E current = next();

        if (!predicate.test(current)) {
            throw new SyntaxException(String.format(
                "The current token (type: '%s', value '%s') does not match with the specified predicate.",
                current.getType(),
                current.getValue()
            ));
        }

        return current;
    }

    @Override
    public void clear() {
        tokens.clear();
        index = 0;
    }

    @Override
    public E peek() {
        return peek(1);
    }

    @Override
    public E peek(int number) {
        return tokens.get(index + number);
    }

    @Override
    public E current() {
        return (!isEmpty()) ? tokens.get(index) : null;
    }

    @Override
    public int size() {
        return tokens.size() - index;
    }
}

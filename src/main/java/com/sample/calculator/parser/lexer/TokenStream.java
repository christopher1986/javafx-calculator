package com.sample.calculator.parser.lexer;

import com.sample.calculator.parser.exception.SyntaxException;
import com.sample.calculator.parser.lexer.exception.EmptyStreamException;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * The TokenStream maintains a collection of Token objects.
 */
public interface TokenStream<E extends Token> {
    /**
     * Append the specified to this stream.
     *
     * @param token The token to add.
     */
    void add(E token);

    /**
     * Append the specified collection of tokens to this stream.
     *
     * @param tokens The tokens to add.
     */
    void addAll(Collection<E> tokens);

    /**
     * Consumes and returns the next in the stream.
     *
     * @return The next token.
     * @throws EmptyStreamException If the stream is empty.
     */
    E next() throws EmptyStreamException;

    /**
     * Returns true if the stream is empty.
     *
     * @return True if the stream has no more tokens.
     */
    boolean isEmpty();

    /**
     * Tests whether the current token matches the specified predicate.
     *
     * @param predicate The predicate that will be tested.
     * @return True if the predicate matches with the current token.
     */
    boolean matches(Predicate<E> predicate);

    /**
     * Consumes the current token which must match with the specified predicate.
     *
     * @param predicate The predicate that will be tested.
     * @return The current token.
     * @throws SyntaxException If the current token does not match the specified predicate.
     */
    E expects(Predicate<E> predicate) throws SyntaxException;

    /**
     * Remove all tokens contained by this stream.
     */
    void clear();

    /**
     * Returns if present the next token in the stream without removing it.
     *
     * @return The next token, or null if there are no more tokens left.
     */
    E peek();

    /**
     * Returns if present a future token without removing it.
     *
     * @param number The number of tokens to lookahead.
     * @return The future token, or null if the future token does not exist.
     */
    E peek(int number);

    /**
     * Returns if present the current token without removing it.
     *
     * @return The current token, or null on failure.
     */
    E current();

    /**
     * Returns the number of tokens still remaining in this stream.
     *
     * @return The number of tokens in this stream.
     */
    int size();

    /**
     * Returns an array containing all of the tokens in this stream in proper sequence.
     *
     * @return an array containing the tokens of the stream.
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the tokens in this stream in proper sequence.
     *
     * @param array the array into which the elements of the stream are to
     *              be stored, if it is big enough; otherwise, a new array of the
     *              same runtime type is allocated for this purpose.
     * @return an array containing the tokens of the stream.
     */
    E[] toArray(E[] array);
}

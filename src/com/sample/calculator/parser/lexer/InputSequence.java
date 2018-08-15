package com.sample.calculator.parser.lexer;

import java.util.Arrays;

/**
 * The InputSequence maintains the characters for which a lexer creates tokens.
 */
public class InputSequence implements CharSequence {
    /**
     * An array of characters.
     */
    private char value[];

    /**
     * The index of the first character to tokenize.
     */
    private int offset;

    /**
     * The total number of characters to tokenize.
     */
    private int size;

    /**
     * Initialize a new InputSequence.
     *
     * @param input The string to use as input.
     */
    public InputSequence(String input)
    {
        value = input.toCharArray();
        size = value.length;
    }

    /**
     * Advance the internal pointer by the specified amount.
     *
     * @param amount The number of character to advance.
     */
    public void advance(int amount) {
        size = Math.max(0, size - amount);
        offset = Math.min(value.length, offset + amount);
    }

    /**
     * Returns true if the internal pointer is beyond the character length.
     *
     * @return True if there no more characters left.
     */
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public char charAt(int index) {
        return value[offset + index];
    }

    /**
     * Returns a string that is a substring of this sequence.
     *
     * @param endIndex the ending index, exclusive.
     * @return The specified substring.
     */
    public String substring(int endIndex) {
        return substring(0, endIndex);
    }

    /**
     * Returns a string that is a substring of this sequence.
     *
     * @param beginIndex the beginning index, inclusive.
     * @param endIndex the ending index, exclusive.
     * @return The specified substring.
     */
    private String substring(int beginIndex, int endIndex) {
        return (String) subSequence(beginIndex, endIndex);
    }

    @Override
    public CharSequence subSequence(int beginIndex, int endIndex) {
        int fromIndex = offset + beginIndex;
        int toIndex = offset + endIndex;

        return new String(Arrays.copyOfRange(value, fromIndex, toIndex));
    }

    @Override
    public String toString() {
        int fromIndex = offset;
        int toIndex = offset + size;

        return new String(Arrays.copyOfRange(value, fromIndex, toIndex));
    }
}

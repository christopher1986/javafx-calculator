package com.sample.calculator.parser.lexer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The InputSequenceTest contains JUnit 5 test for the InputSequence class.
 */
class InputSequenceTest
{
    private InputSequence input;

    @BeforeEach
    void setUp() {
        input = new InputSequence("Input string!");
    }

    @Test
    void testAdvance() {
        input.advance(10);

        assertEquals(3, input.length());
    }

    @Test
    void testIsNotEmpty() {
        assertFalse(input.isEmpty());
        assertNotEquals(0, input.length());
    }

    @Test
    void testIsEmpty() {
        input.advance(13);

        assertTrue(input.isEmpty());
        assertEquals(0, input.length());
    }

    @Test
    void testLength() {
        assertEquals(13, input.length());
    }

    @Test
    void testCharAt() {
        assertEquals('p', input.charAt(2));
        assertEquals('!', input.charAt(12));
    }

    @Test
    void testSubstring() {
        assertEquals("Input", input.substring(5));
    }

    @Test
    void testSubSequence() {
        assertEquals("string", input.subSequence(6, 12));
    }

    @Test
    void testOutOfBoundsSubSequence() {
        assertThrows(IndexOutOfBoundsException.class, () -> { input.subSequence(0, 20); });
        assertThrows(IndexOutOfBoundsException.class, () -> { input.subSequence(30, 31); });
        assertThrows(IndexOutOfBoundsException.class, () -> { input.subSequence(10, 8); });
    }

    @Test
    void testToString() {
        assertEquals("Input string!", input.toString());
    }
}
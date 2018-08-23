package com.sample.calculator.parser.lexer;

import com.sample.calculator.parser.exception.SyntaxException;
import com.sample.calculator.parser.lexer.Token.Type;
import com.sample.calculator.parser.lexer.exception.EmptyStreamException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TokenStreamImplTest contains JUnit 5 test for the TokenStreamImpl class.
 */
class TokenStreamImplTest
{
    private TokenStream<Token> tokens;

    @BeforeEach
    void setUp()
    {
        tokens = new TokenStreamImpl<Token>() {{
            add(new TokenImpl(Type.NUMBER, "10"));
            add(new TokenImpl(Type.PARENTHESIS, "("));
        }};
    }

    @Test
    void testAddToken()
    {
        tokens.add(new TokenImpl(Type.NUMBER, "20"));

        assertEquals(3, tokens.size());
    }

    @Test
    void testAddTokens()
    {
        Collection<Token> items = new ArrayList<Token>() {{
            add(new TokenImpl(Type.NUMBER, "20"));
            add(new TokenImpl(Type.NUMBER, "25"));
        }};

        tokens.addAll(items);

        assertEquals(4, tokens.size());
    }

    @Test
    void testNext()
    {
        Token expected = new TokenImpl(Type.NUMBER, "10");

        assertEquals(expected, tokens.next());
    }

    @Test
    void testIllegalNext()
    {
        tokens.next();
        tokens.next();

        assertThrows(EmptyStreamException.class, () -> { tokens.next(); });
    }

    @Test
    void testIsNotEmpty()
    {
        assertFalse(tokens.isEmpty());
        assertNotEquals(0, tokens.size());
    }

    @Test
    void testIsEmpty()
    {
        tokens.clear();

        assertTrue(tokens.isEmpty());
        assertEquals(0, tokens.size());
    }

    @Test
    void testMatches()
    {
        Predicate<Token> predicate = (Token token) -> token.getValue().equals("10") && token.getType().equals(Type.NUMBER);

        assertTrue(tokens.matches(predicate));
    }

    @Test
    void testNotMatches()
    {
        Predicate<Token> predicate = (Token token) -> token.equalsValue("+") && token.equalsType(Type.OPERATOR);

        assertFalse(tokens.matches(predicate));
    }

    @Test
    void testExpects()
    {
        Predicate<Token> predicate = (Token token) -> token.equalsValue("10") && token.equalsType(Type.NUMBER);

        assertDoesNotThrow(() -> tokens.expects(predicate));
    }

    @Test
    void testWrongExpects()
    {
        Predicate<Token> predicate = (Token token) -> token.equalsValue("-") && token.equalsType(Type.OPERATOR);

        assertThrows(SyntaxException.class, () -> tokens.expects(predicate));
    }

    @Test
    void testClear()
    {
        assertNotEquals(0, tokens.size());

        tokens.clear();

        assertEquals(0, tokens.size());
    }

    @Test
    void testPeek()
    {
        Token expected = new TokenImpl(Type.NUMBER, "10");
        Token received = tokens.peek(0);

        assertEquals(expected, received);
    }

    @Test
    void testOutOfBoundsPeek()
    {
        assertThrows(IndexOutOfBoundsException.class, () -> { tokens.peek(10); });
    }

    @Test
    void testPeek1()
    {
        Token expected = new TokenImpl(Type.PARENTHESIS, "(");
        Token received = tokens.peek();

        assertEquals(expected, received);
    }

    @Test
    void testCurrent()
    {
        Token expected = new TokenImpl(Type.NUMBER, "10");

        assertEquals(expected, tokens.current());
    }

    @Test
    void testNotCurrent()
    {
        Token expected = new TokenImpl(Type.OPERATOR, "-");

        assertNotEquals(expected, tokens.current());
    }

    @Test
    void testSize()
    {
        assertEquals(2, tokens.size());
    }
}
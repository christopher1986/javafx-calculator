package com.sample.calculator.parser;

import com.sample.calculator.parser.exception.SyntaxException;
import com.sample.calculator.parser.lexer.Token;
import com.sample.calculator.parser.lexer.Token.Type;
import com.sample.calculator.parser.lexer.TokenImpl;
import com.sample.calculator.parser.lexer.TokenStream;
import com.sample.calculator.parser.operator.Operator;
import com.sample.calculator.parser.operator.binary.AdditionOperator;
import com.sample.calculator.parser.operator.binary.DivisionOperator;
import com.sample.calculator.parser.operator.unary.MinusOperator;
import com.sample.calculator.parser.operator.unary.PlusOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class LexerImplTest
{
    private Lexer lexer;

    @BeforeEach
    void setUp()
    {
        Collection<Operator> operators = new HashSet<Operator>() {{
            add(new AdditionOperator());
            add(new DivisionOperator());
            add(new PlusOperator());
            add(new MinusOperator());
        }};

        lexer = new LexerImpl(operators);
    }

    @Test
    void testTokenizeNumber()
    {
        TokenStream<Token> tokens = lexer.tokenize("2");
        Object[] received = tokens.toArray();
        Object[] expected = new Token[] {
            new TokenImpl(Type.NUMBER, "2")
        };

        assertTrue(Arrays.equals(expected, received));
    }

    @Test
    void testTokenizeExpression()
    {
        TokenStream<Token> tokens = lexer.tokenize("2+2");
        Object[] received = tokens.toArray();
        Object[] expected = new Token[] {
            new TokenImpl(Type.NUMBER, "2"),
            new TokenImpl(Type.OPERATOR, "+"),
            new TokenImpl(Type.NUMBER, "2")
        };

        assertTrue(Arrays.equals(expected, received));
    }

    @Test
    void testTokenizeParenthesis()
    {
        TokenStream<Token> tokens = lexer.tokenize("(6/3)");
        Object[] received = tokens.toArray();
        Object[] expected = new Token[] {
            new TokenImpl(Type.PARENTHESIS, "("),
            new TokenImpl(Type.NUMBER, "6"),
            new TokenImpl(Type.OPERATOR, "/"),
            new TokenImpl(Type.NUMBER, "3"),
            new TokenImpl(Type.PARENTHESIS, ")"),
        };

        assertTrue(Arrays.equals(expected, received));
    }

    @Test
    void testMissingParentheses()
    {
        assertThrows(SyntaxException.class, () -> { lexer.tokenize("(6+3"); });
        assertThrows(SyntaxException.class, () -> { lexer.tokenize("((12+6)"); });
    }

    @Test
    void testIllegalSymbols()
    {
        assertThrows(SyntaxException.class, () -> { lexer.tokenize("[12/6]"); });
        assertThrows(SyntaxException.class, () -> { lexer.tokenize("(a+b)"); });
        assertThrows(SyntaxException.class, () -> { lexer.tokenize("12*2"); });
    }

    @Test
    void testIllegalWhitespace()
    {
        assertThrows(SyntaxException.class, () -> { lexer.tokenize("18 / 6"); });
    }
}
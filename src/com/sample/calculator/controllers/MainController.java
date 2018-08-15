package com.sample.calculator.controllers;

import com.sample.calculator.parser.Parser;
import com.sample.calculator.parser.ParserImpl;
import com.sample.calculator.parser.node.ExpressionNode;
import com.sample.calculator.parser.operator.BinaryOperator;
import com.sample.calculator.parser.operator.UnaryOperator;
import com.sample.calculator.parser.operator.binary.AdditionOperator;
import com.sample.calculator.parser.operator.binary.DivisionOperator;
import com.sample.calculator.parser.operator.binary.MultiplicationOperator;
import com.sample.calculator.parser.operator.binary.SubtractionOperator;
import com.sample.calculator.parser.operator.unary.MinusOperator;
import com.sample.calculator.parser.operator.unary.PlusOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashSet;
import java.util.Set;

/**
 * The MainController wires the graphical user interface (GUI) to programing logic.
 */
public class MainController
{
    /**
     * The State enum contains valid states.
     */
    private enum State { OK, ERROR }

    /**
     * The current state.
     */
    private State context = State.OK;

    /**
     * A collection of binary operators.
     */
    private Set<BinaryOperator> binaryOperators = new HashSet<BinaryOperator>() {{
        add(new AdditionOperator());
        add(new SubtractionOperator());
        add(new MultiplicationOperator());
        add(new DivisionOperator());
    }};

    /**
     * A collection of unary operators.
     */
    private Set<UnaryOperator> unaryOperators = new HashSet<UnaryOperator>() {{
        add(new PlusOperator());
        add(new MinusOperator());
    }};

    /**
     * The expression parser.
     */
    private Parser parser = new ParserImpl(binaryOperators, unaryOperators);

    @FXML
    private Label output;

    @FXML
    private void onButtonClick(ActionEvent event) {
        String newValue = ((Button) event.getSource()).getText();
        String previous = output.getText();

        if (context.equals(State.OK)) {
            output.setText(previous + newValue);
        }
    }

    @FXML
    private void onClearClick() {
        context = State.OK;
        output.setText("");
    }

    @FXML
    private void onComputeClick() {
        try {
            ExpressionNode node = parser.parse(output.getText());
            output.setText(node.evaluate().toPlainString());
        } catch (RuntimeException e) {
            context = State.ERROR;
            output.setText("Malformed expression");
        }
    }
}

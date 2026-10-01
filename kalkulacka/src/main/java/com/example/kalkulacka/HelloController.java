package com.example.kalkulacka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML private Label displayLabel;
    @FXML private Label historyLabel;

    private double firstOperand;
    private String currentOperator = "";
    private boolean startNewNumber = true;

    @FXML void handleNumberButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        String digit = button.getText();

        if (startNewNumber) {
            displayLabel.setText(digit);
            startNewNumber=false;
        } else {
            if (displayLabel.getText().equals("0")) {
                displayLabel.setText(digit);
            } else {
                displayLabel.setText(displayLabel.getText() + digit);
            }
        }
    }
    @FXML void handleOperationButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        currentOperator = button.getText();

        try {
            firstOperand = Double.parseDouble(displayLabel.getText());
        } catch (NumberFormatException e) {
            firstOperand = 0;
        }

        historyLabel.setText(firstOperand + " " + currentOperator);
        startNewNumber = true;
    }

    @FXML void handleEqualsButton(ActionEvent event) {
        if (currentOperator.isEmpty()) return;

        double secondOperand;
        try {
            secondOperand = Double.parseDouble(displayLabel.getText());
        } catch (NumberFormatException e) {
            secondOperand = 0;
        }

        double result = 0;
        switch (currentOperator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "X":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    displayLabel.setText("Chyba");
                    return;
                }
                break;
        }

        displayLabel.setText(String.valueOf(result));
        historyLabel.setText(firstOperand + " " + currentOperator + " " + secondOperand);
        currentOperator = "";
        startNewNumber = true;
    }

    @FXML void handleClearButton(ActionEvent event){
        displayLabel.setText("0");
        historyLabel.setText("");
        firstOperand = 0;
        currentOperator = "";
        startNewNumber = true;
        }
    }
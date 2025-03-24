package com.calculator.logic;

import com.calculator.ui.Display;
import java.util.Stack;

public class ScientificCalculatorLogic {
    private double result = 0;
    private String lastCommand = "=";
    private boolean start = true;
    private Stack<Double> memory = new Stack<>();

    public void processNumber(String digit, Display display) {
        if (start) {
            display.setText(digit);
            start = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    public void processOperator(String command, Display display) {
        double currentValue;
        try {
            currentValue = Double.parseDouble(display.getText());
        } catch (NumberFormatException e) {
            currentValue = result;
        }

        switch (command) {
            case "C":
                start = true;
                result = 0;
                lastCommand = "=";
                memory.clear();
                display.setText("0");
                break;
            case "Back":
                String text = display.getText();
                if (text.length() > 1) {
                    display.setText(text.substring(0, text.length() - 1));
                } else {
                    display.setText("0");
                    start = true;
                }
                break;
            case "±":
                display.setText(String.valueOf(-Double.parseDouble(display.getText())));
                break;
            case "sin":
                display.setText(String.valueOf(Math.sin(Math.toRadians(currentValue))));
                start = true;
                break;
            case "cos":
                display.setText(String.valueOf(Math.cos(Math.toRadians(currentValue))));
                start = true;
                break;
            case "tan":
                display.setText(String.valueOf(Math.tan(Math.toRadians(currentValue))));
                start = true;
                break;
            case "log":
                display.setText(String.valueOf(Math.log10(currentValue)));
                start = true;
                break;
            case "ln":
                display.setText(String.valueOf(Math.log(currentValue)));
                start = true;
                break;
            case "x²":
                display.setText(String.valueOf(currentValue * currentValue));
                start = true;
                break;
            case "√":
                display.setText(String.valueOf(Math.sqrt(currentValue)));
                start = true;
                break;
            case "xʸ":
                memory.push(currentValue);
                display.setText("0");
                start = true;
                lastCommand = "xʸ";
                break;
            case "10ˣ":
                display.setText(String.valueOf(Math.pow(10, currentValue)));
                start = true;
                break;
            case "π":
                display.setText(String.valueOf(Math.PI));
                start = false;
                break;
            case "e":
                display.setText(String.valueOf(Math.E));
                start = false;
                break;
            default:
                if (start) {
                    if (command.equals("-")) {
                        display.setText(command);
                        start = false;
                    } else {
                        lastCommand = command;
                    }
                } else {
                    calculate(currentValue, display);
                    lastCommand = command;
                    start = true;
                }
        }
    }

    private void calculate(double x, Display display) {
        switch (lastCommand) {
            case "+": result += x; break;
            case "-": result -= x; break;
            case "*": result *= x; break;
            case "/": if (x != 0) result /= x; break;
            case "=": result = x; break;
            case "xʸ": 
                if (!memory.isEmpty()) {
                    result = Math.pow(memory.pop(), x);
                }
                break;
        }
        display.setText(String.valueOf(result));
    }
}
package com.calculator;

import com.calculator.ui.CalculatorUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorUI calc = new CalculatorUI();
            calc.setVisible(true);
        });
    }
}
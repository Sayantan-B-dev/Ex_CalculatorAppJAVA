package com.calculator.ui;

import javax.swing.*;
import java.awt.*;
import com.calculator.logic.ScientificCalculatorLogic;
import javax.swing.border.EmptyBorder;

public class ButtonPanel {
    private JPanel panel;
    private ScientificCalculatorLogic logic;
    private Display display;

    public ButtonPanel(ScientificCalculatorLogic logic, Display display) {
        this.logic = logic;
        this.display = display;

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 4, 5, 5)); // Adjusted grid to 6x6 for better spacing

        String[] buttonLabels = {
            "sin", "cos", "tan", "log",  // Row 1: Trigonometric and Logarithmic functions
            "ln", "x²", "√", "xʸ",        // Row 2: Math functions
            "7", "8", "9", "/",           // Row 3: Numbers and division
            "4", "5", "6", "*",           // Row 4: Numbers and multiplication
            "1", "2", "3", "-",           // Row 5: Numbers and subtraction
            "0", ".", "=", "+",           // Row 6: Numbers and basic operations
            "(", ")", "π", "e",           // Row 7: Parentheses and constants
            "±", "C", "Back", "10ˣ"       // Row 8: Special and control buttons
        };

        for (String label : buttonLabels) {
            if (label == null) {
                panel.add(new JLabel()); // Add empty JLabel for null labels
            } else {
                JButton button = new JButton(label);
                button.setFont(new Font("Arial", Font.PLAIN, 18));
                panel.add(button);
                if (Character.isDigit(label.charAt(0)) || label.equals(".")) {
                    button.addActionListener(e -> logic.processNumber(label, display));
                } else {
                    button.addActionListener(e -> logic.processOperator(label, display));
                }
            }
        }
        panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding around the panel
    }

    public JComponent getComponent() {
        return panel;
    }
}
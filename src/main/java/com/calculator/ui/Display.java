package com.calculator.ui;

import javax.swing.*;
import java.awt.*;

public class Display {
    private JTextField displayField;

    public Display() {
        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));
        displayField.setPreferredSize(new Dimension(300, 50));
    }

    public JComponent getComponent() {
        return displayField;
    }

    public void setText(String text) {
        displayField.setText(text);
    }

    public String getText() {
        return displayField.getText();
    }
}
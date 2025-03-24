package com.calculator.ui;

import javax.swing.*;
import java.awt.*;
import com.calculator.logic.ScientificCalculatorLogic;
import javax.swing.border.LineBorder; // Import LineBorder

public class CalculatorUI extends JFrame {
    private Display display;
    private ButtonPanel buttonPanel;
    private ScientificCalculatorLogic logic;

    public CalculatorUI() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        setResizable(false);

        JLabel infoLabel = new JLabel("SayantanBharati (24/KPC/CST(V)/037)");
        
        logic = new ScientificCalculatorLogic();
        display = new Display();
        buttonPanel = new ButtonPanel(logic, display);

        // Add border to the ButtonPanel
        buttonPanel.getComponent().setBorder(new LineBorder(Color.WHITE, 10)); // Added border here
        
        add(infoLabel, BorderLayout.SOUTH);
        add(display.getComponent(), BorderLayout.NORTH);
        add(buttonPanel.getComponent(), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
}
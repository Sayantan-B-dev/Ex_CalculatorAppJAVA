import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIManager extends JFrame implements ActionListener {
    private JTextField input1, input2, result;
    private JButton[] operationButtons;
    private JButton calculate, clear;
    private CalculatorOperations operations;

    public UIManager() {
        super("Scientific Calculator");
        operations = new CalculatorOperations();

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(220, 210, 180)); // Deeper elegant cream

        mainPanel.add(createInputPanel(), BorderLayout.NORTH);
        mainPanel.add(createOperationPanel(), BorderLayout.CENTER);
        mainPanel.add(createResultPanel(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        input1 = createStyledTextField();
        input2 = createStyledTextField();
        result = createStyledTextField();
        result.setEditable(false);
        result.setBackground(Color.LIGHT_GRAY);
        result.setForeground(Color.black);
        result.setFont(new Font("Arial", Font.BOLD, 12));
        result.setHorizontalAlignment(JTextField.CENTER);
        panel.add(createFormField("Num 1:", input1));
        panel.add(createFormField("Num 2 (Optional):", input2));
        panel.add(createFormField("Result:", result));
        return panel;
    }

    private JPanel createFormField(String labelText, JTextField textField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setOpaque(false);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setMaximumSize(new Dimension(200, 30));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(5));
        fieldPanel.add(textField);
        fieldPanel.add(Box.createVerticalStrut(5));
        return fieldPanel;
    }

    private JPanel createOperationPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 4, 10, 10)); // 5 rows, 4 columns grid
        panel.setOpaque(false);

        String[] operationsList = { "+", "-", "*", "/", "^", "√", "%", "log", "ln", "e^x",
                "sin", "cos", "tan", "asin", "acos", "atan", "abs",
                "factorial", "sinh", "cosh", "tanh" };

        operationButtons = new JButton[operationsList.length];

        for (int i = 0; i < operationsList.length; i++) {
            JButton button = createStyledButton(operationsList[i], Color.CYAN);
            button.addActionListener(this);
            operationButtons[i] = button;
            panel.add(button);
        }

        return panel;
    }

    private void performOperation(String operation) {
        try {
            double num1 = input1.getText().isEmpty() ? 0 : Double.parseDouble(input1.getText());
            double num2 = input2.getText().isEmpty() ? 0 : Double.parseDouble(input2.getText());

            // If operation requires only one number (like sqrt, log), we set num2 to 0
            if (operation.matches("√|log|ln|e\\^x|sin|cos|tan|asin|acos|atan|abs|factorial|sinh|cosh|tanh")) {
                num2 = 0;
            }

            double output = operations.performOperation(num1, num2, operation);
            result.setText(String.valueOf(output));
        } catch (NumberFormatException ex) {
            result.setText("Invalid Input");
        } catch (ArithmeticException ex) {
            result.setText("Error: " + ex.getMessage());
        }
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panel.setOpaque(false);
        calculate = createStyledButton2("Calculate", Color.BLUE);
        clear = createStyledButton2("Clear", Color.RED);
        calculate.addActionListener(this);
        clear.addActionListener(this);
        panel.add(calculate);
        panel.add(clear);
        return panel;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return textField;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.black);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Change the background color when the mouse enters and exits
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }
    private JButton createStyledButton2(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15) // Increased padding
        ));
    
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { button.setBackground(bgColor.darker()); }
            public void mouseExited(MouseEvent e) { button.setBackground(bgColor); }
        });
    
        return button;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if it's one of the operation buttons
        for (JButton button : operationButtons) {
            if (e.getSource() == button) {
                performOperation(button.getText());
                return;
            }
        }

        // If Calculate or Clear is pressed
        if (e.getSource() == clear) {
            input1.setText("");
            input2.setText("");
            result.setText("");
            return;
        }

        // Handle Calculate button separately
        if (e.getSource() == calculate) {
            double num1 = input1.getText().isEmpty() ? 0 : Double.parseDouble(input1.getText());
            double num2 = input2.getText().isEmpty() ? 0 : Double.parseDouble(input2.getText());
            // You don't need to check for a selected operation, as this is handled by the
            // button
            double output = operations.performOperation(num1, num2, "calculate"); // Modify as needed if any specific
                                                                                  // operation is performed
            result.setText(String.valueOf(output));
        }
    }

    public static void main(String[] args) {
        new UIManager();
    }
}

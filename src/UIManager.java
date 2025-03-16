import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIManager extends JFrame implements ActionListener {
    private JTextField input1, input2, result;
    private JComboBox<String> operation;
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
        setSize(300, 400);
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
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(5));
        fieldPanel.add(textField);
        fieldPanel.add(Box.createVerticalStrut(5));
        return fieldPanel;
    }

    private JPanel createOperationPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setOpaque(false);
        String[] operationsList = {"+", "-", "*", "/", "^", "√", "%", "log", "ln", "e^x",
                                    "sin", "cos", "tan", "asin", "acos", "atan", "abs",
                                    "factorial", "sinh", "cosh", "tanh"};
        operation = new JComboBox<>(operationsList);
        operation.setFont(new Font("Arial", Font.BOLD, 14));
        operation.setPreferredSize(new Dimension(100, 30));
        JLabel opLabel = new JLabel("Select Operation:");
        opLabel.setFont(new Font("Arial", Font.BOLD, 11));
        opLabel.setForeground(Color.BLACK);
        panel.add(opLabel);
        panel.add(operation);
        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panel.setOpaque(false);
        calculate = createStyledButton("Calculate", Color.BLUE);
        clear = createStyledButton("Clear", Color.RED);
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
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g2);
                g2.dispose();
            }
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                g2.dispose();
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            input1.setText("");
            input2.setText("");
            result.setText("");
            return;
        }
        try {
            String selectedOperation = (String) operation.getSelectedItem();
            double num1 = input1.getText().isEmpty() ? 0 : Double.parseDouble(input1.getText());
            double num2 = input2.getText().isEmpty() ? 0 : Double.parseDouble(input2.getText());
            if (selectedOperation.matches("√|log|ln|e\\^x|sin|cos|tan|asin|acos|atan|abs|factorial|sinh|cosh|tanh"))
                num2 = 0;
            double output = operations.performOperation(num1, num2, selectedOperation);
            result.setText(String.valueOf(output));
        } catch (NumberFormatException ex) {
            result.setText("Invalid Input");
        } catch (ArithmeticException ex) {
            result.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new UIManager();
    }
}

import java.util.HashMap;
import java.util.Map;

public class CalculatorOperations {
    private static final Map<String, Double> memory = new HashMap<>(); // For memory functions

    public double performOperation(double num1, double num2, String operation) {
        switch (operation.toLowerCase()) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 == 0) throw new ArithmeticException("Division by zero");
                return num1 / num2;
            case "^": return Math.pow(num1, num2);
            case "√":
                if (num1 < 0) throw new ArithmeticException("Negative square root");
                return Math.sqrt(num1);
            case "%":
                if (num2 == 0) throw new ArithmeticException("Modulus by zero");
                return num1 % num2;
            case "log":
                if (num1 <= 0) throw new ArithmeticException("Logarithm of non-positive number");
                return Math.log10(num1);
            case "ln":
                if (num1 <= 0) throw new ArithmeticException("Natural log of non-positive number");
                return Math.log(num1);
            case "e^x": return Math.exp(num1);
            case "sin": return Math.sin(Math.toRadians(num1));
            case "cos": return Math.cos(Math.toRadians(num1));
            case "tan": return Math.tan(Math.toRadians(num1));
            case "asin":
                if (num1 < -1 || num1 > 1) throw new ArithmeticException("Input out of range for asin");
                return Math.toDegrees(Math.asin(num1));
            case "acos":
                if (num1 < -1 || num1 > 1) throw new ArithmeticException("Input out of range for acos");
                return Math.toDegrees(Math.acos(num1));
            case "atan": return Math.toDegrees(Math.atan(num1));
            case "abs": return Math.abs(num1);
            case "factorial":
                if (num1 < 0 || num1 != (int) num1) throw new ArithmeticException("Factorial of negative or non-integer number not allowed");
                return factorial((int) num1);
            case "sinh": return Math.sinh(num1);
            case "cosh": return Math.cosh(num1);
            case "tanh": return Math.tanh(num1);
            case "store": memory.put("M", num1); return num1; // Store in memory
            case "recall": return memory.getOrDefault("M", 0.0); // Recall from memory
            case "clearM": memory.clear(); return Double.NaN; // Clear memory, returning NaN as confirmation
            default: throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }

    private double factorial(int n) {
        if (n < 0) throw new ArithmeticException("Factorial of negative number not allowed");
        double fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}

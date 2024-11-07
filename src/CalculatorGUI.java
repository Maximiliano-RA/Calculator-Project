import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField display;
    private int firstNumber;
    private int secondNumber;
    private String operator;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            firstNumber = secondNumber = 0;
            operator = "";
        } else if (command.equals("=")) {
            secondNumber = Integer.parseInt(display.getText());
            int result = 0;
            switch (operator) {
                case "+":
                    result = Calculator.add(firstNumber, secondNumber);
                    break;
                case "-":
                    result = Calculator.subtract(firstNumber, secondNumber);
                    break;
                case "*":
                    result = Calculator.multiply(firstNumber, secondNumber);
                    break;
                case "/":
                    result = Calculator.divide(firstNumber, secondNumber);
                    break;
            }
            display.setText(String.valueOf(result));
        } else {
            firstNumber = Integer.parseInt(display.getText());
            operator = command;
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
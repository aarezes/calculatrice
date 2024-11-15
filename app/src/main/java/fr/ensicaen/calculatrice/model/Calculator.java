package fr.ensicaen.calculatrice.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Stack;

public class Calculator {

    private static final char ADD_OPERATOR = '+';
    private static final char SUBSTRACT_OPERATOR = '-';
    private static final char MULTYPLY_OPERATOR = 'x';
    private static final char DIVIDE_OPERATOR = '/';

    private Mode _mode = Mode.NONE;


    private enum Mode {
        NONE,
        EQUALS
    }

    public void noneMode() { _mode = Mode.NONE; }
    public void equalsMode() {
        _mode = Mode.EQUALS;
    }

    public double calculate(String calcul) throws DivideByZeroException, InvalidFormatException {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int length = calcul.length();

        for (int i = 0; i < length; i++) {
            char c = calcul.charAt(i);
            if (Character.isDigit(c) || c == ',') {
                int j = i;
                while (j < length && (Character.isDigit(calcul.charAt(j)) || calcul.charAt(j) == ',')) {
                    j++;
                }

                String number = calcul.substring(i, j).replace(',', '.');
                if (!hasNotManyComa(number)) {
                    throw new InvalidFormatException();

                }

                values.push(Double.parseDouble(number));
                i = j - 1;
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return formatResult(values.pop());
    }

    public boolean isOperator(char c) {
        return c == ADD_OPERATOR || c == SUBSTRACT_OPERATOR || c == MULTYPLY_OPERATOR || c == DIVIDE_OPERATOR;
    }

    private boolean hasPrecedence(char op1, char op2) {
        return (op2 == MULTYPLY_OPERATOR || op2 == DIVIDE_OPERATOR) && (op1 == ADD_OPERATOR || op1 == SUBSTRACT_OPERATOR);
    }

    private double applyOperator(char operator, double b, double a) throws DivideByZeroException {
        switch (operator) {
            case ADD_OPERATOR: return a + b;
            case SUBSTRACT_OPERATOR: return a - b;
            case MULTYPLY_OPERATOR: return a * b;
            case DIVIDE_OPERATOR:
                if (b == 0) throw new DivideByZeroException();
                return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private double formatResult(double result) {
        DecimalFormat df = new DecimalFormat("#.#####", DecimalFormatSymbols.getInstance(Locale.US));
        return Double.parseDouble(df.format(result));
    }
    public boolean isNone () {
        return _mode == Mode.EQUALS;
    }

    public static boolean hasNotManyComa(String nb) {
        return nb.length() - nb.replace(".", "" ).length() <= 1;
    }

}
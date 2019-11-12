package ru.job4j.calculator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.11.2019.
 */
public class InteractCalc {
    private Calculator calc;
    private double lastResult;
    private Scanner scanner;
    private OutputStream out;

    /**
     * Default constructor.
     */
    public InteractCalc() {
        this.scanner = new Scanner(System.in);
        this.calc = new Calculator();
    }


    /**
     * Constructor for test.
     *
     * @param in  - InputStream.
     * @param out - OutputStream.
     */
    public InteractCalc(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.calc = new Calculator();
        this.out = out;
    }

    /**
     * Calculate.
     */
    public void calculate() {
        double first;
        double second;
        String operator;
        double result = 0;
        String finish;
        while (true) {
            if (lastResult != 0) {
                write("\nUse last result? (Yes/No)");
                String answer = this.scanner.next();
                if (answer.equals("Yes")) {
                    first = this.lastResult;
                } else {
                    lastResult = 0;
                    continue;
                }
            } else {
                write("\nPlease, write first value : ");
                first = this.scanner.nextDouble();

            }
            this.write("\nPlease, write operator : ");
            operator = this.scanner.next();
            this.write("\nPlease, write second value : ");
            second = this.scanner.nextDouble();
            if (operator.equals("+")) {
                result = this.calc.add(first, second);
            } else if (operator.equals("-")) {
                result = this.calc.subtract(first, second);
            } else if (operator.equals("*")) {
                result = this.calc.multiple(first, second);
            } else if (operator.equals("/")) {
                result = this.calc.div(first, second);
            } else {
                try {
                    throw new Exception("\nSorry, i don't know this operator. ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            write(String.format("%s%s", "\nYour result = ", result));
            write("\nContinue? Yse/No : ");
            finish = this.scanner.next();
            if (finish.equals("No")) {
                write("\nBye!");
                break;
            }
            this.lastResult = result;
        }
    }

    /**
     * Write a message in console.
     *
     * @param message - text of message.
     */
    private void write(String message) {
        System.out.print(message);
    }

    @Override
    public String toString() {
        return String.format("%s%s", "Last result is ", this.lastResult);
    }
}

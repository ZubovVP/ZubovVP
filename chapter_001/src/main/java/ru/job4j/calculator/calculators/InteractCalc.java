package ru.job4j.calculator.calculators;

import ru.job4j.calculator.Validator;

import java.io.InputStream;

import java.util.Scanner;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 09.12.2019.
 */
public class InteractCalc {
    private Calculator calc;
    private double lastResult;
    private Scanner scanner;
    private Validator validator;

    /**
     * Default constructor.
     */
    public InteractCalc(Calculator calculator, Validator validator) {
        this.scanner = new Scanner(System.in);
        this.calc = calculator;
        this.validator = validator;
    }


    /**
     * Constructor for test.
     *
     * @param in - InputStream.
     */
    public InteractCalc(Calculator calculator, Validator validator, InputStream in) {
        this.scanner = new Scanner(in);
        this.calc = calculator;
        this.validator = validator;
    }

    /**
     * Calculate.
     */
    public void calculate() {
        write("Please, write for start : ");
        boolean start = this.scanner.nextLine().equals("start");
        while (start) {
            write("Please, write an example : ");
            String[] line = this.scanner.nextLine().split(" ");
            this.lastResult = this.validator.validate(line);
            write(String.format("%s%s\n", "Result = ", String.valueOf(this.lastResult)));
            write("Continue? Yes/No");
            start = this.scanner.nextLine().equals("Yes");
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


package ru.job4j.start;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
@Component
public class ValidateInput implements Input {

    private final ConsoleInput input;

    public ValidateInput(ConsoleInput input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, List<UserAction> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value =  this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
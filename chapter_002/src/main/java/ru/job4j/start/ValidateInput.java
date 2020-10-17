package ru.job4j.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
@Component
@Scope("singleton")
public class ValidateInput implements Input {
    @Autowired
    private ConsoleInput input;

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, List<UserAction> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }

    public ConsoleInput getInput() {
        return input;
    }

    public void setInput(ConsoleInput input) {
        this.input = input;
    }
}
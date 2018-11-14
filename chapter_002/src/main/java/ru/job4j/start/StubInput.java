package ru.job4j.start;

import java.util.List;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    public String ask(String question) {
        return  answers[position++];
    }
    public  int ask(String question, List<UserAction> range) {
        return Integer.valueOf(answers[position++]);
    }
}

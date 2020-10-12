package ru.job4j.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 11.10.2020.
 */
public class StubInputForTest extends ConsoleInput {
    private String[] answers;
    private int position = 0;

    public StubInputForTest(String[] answers) {
        this.answers = answers;
    }

    public String ask(String question) {
        return  answers[position++];
    }
    public  int ask(String question, List<UserAction> range) {
        return Integer.valueOf(answers[position++]);
    }
}

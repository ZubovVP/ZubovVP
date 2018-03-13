package ru.job4j.start;

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
    public  int ask(String question, int[] range) {
        //throw new UnsupportedOperationException("Unsupported operation");
        return Integer.valueOf(answers[position++]);
    }
}

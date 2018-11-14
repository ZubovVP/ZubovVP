package ru.job4j.start;

import java.util.List;
import java.util.Scanner;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, List<UserAction> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (UserAction ua : range) {
            if (ua.key() == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("out of menu range. ");
        }
    }
}

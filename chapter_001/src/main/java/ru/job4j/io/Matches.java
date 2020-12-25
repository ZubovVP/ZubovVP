package ru.job4j.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 16.12.2020.
 */
public class Matches {
    private int matches;
    private boolean flag = true;
    private StringBuilder sb = new StringBuilder();
    private Scanner scanner = new Scanner(System.in);

    public Matches(int matches) {
        this.matches = matches;
    }

    public Matches() {
        this.matches = 11;
    }

    public void start() {
        while (this.matches >= 0) {
            this.sb.setLength(0);
            if (this.flag) {
                this.sb.append("Turn Player 1: \n");

            } else {
                this.sb.append("Turn Player 2: \n");

            }
            this.sb.append(this.matches).append(" on the table \n");
            this.sb.append("How many matches do you take? \n");
            ask(this.sb.toString());
            int get = this.scanner.nextInt();
            if (get < 1 || get > 3) {
                ask("Wrong among, please write correct (1, 2, 3)");
                continue;
            }
            getMatches(get);
            this.flag = !this.flag;
        }
        ask(flag ? "Player 2 win!" : "Player 1 win!");
    }

    private void getMatches(int matches) {
        this.matches -= matches;
    }

    private void ask(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        Matches matches = new Matches();
        matches.start();
    }

}

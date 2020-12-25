package ru.job4j.io;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 16.12.2020.
 */
public class MagicBall {
    private List<String> answers;
    private Scanner scanner = new Scanner(System.in);

    public MagicBall(List<String> answers) {
        this.answers = answers;
    }

    public void start() {
        String question;
        Random random = new Random();
        while (true) {
            ask("Я великий Оракул. Что ты хочешь узнать? ");
            question = this.scanner.nextLine().toLowerCase();
            if (question.equals("finish")) {
                ask("Пока!");
                break;
            }
            ask(this.answers.get(random.nextInt(this.answers.size())));
        }
    }

    private void ask(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        MagicBall ball = new MagicBall(List.of("Да", "Нет", "Может быть"));
        ball.start();
    }

}

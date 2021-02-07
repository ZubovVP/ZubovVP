package ru.job4j.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 07.02.2021.
 */
public class ConsoleProgress implements Runnable {
    private static int position = 0;
    private static String[] elements = new String[]{"/", "|", "\\"};

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            print();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private static void print() {
        System.out.print("\r load: " + elements[position]);
        position = position >= 2 ? 0 : position + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}

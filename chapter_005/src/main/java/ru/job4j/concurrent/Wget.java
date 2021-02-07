package ru.job4j.concurrent;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 07.02.2021.
 */
public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {
            for (int x = 0; x < 100; x++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print(x);
            }
        });
        first.start();
    }

    public static void print(int index) {
        System.out.print("\rLoading : " + index + "%");
    }
}

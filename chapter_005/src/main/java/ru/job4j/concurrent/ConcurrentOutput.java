package ru.job4j.concurrent;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 07.02.2021.
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}

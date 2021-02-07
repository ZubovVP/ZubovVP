package ru.job4j.concurrent;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 07.02.2021.
 */
public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Runnable run = () -> print(Thread.currentThread().getName());
        Thread first = new Thread(run);
        Thread second = new Thread(run);
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            Thread.sleep(100);
        }
        print("Finished all threads.");
    }

    public static void print(String name) {
        System.out.println(name);
    }
}

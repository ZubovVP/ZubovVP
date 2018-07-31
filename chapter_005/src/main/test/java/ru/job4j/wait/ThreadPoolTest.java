package ru.job4j.wait;

import org.junit.Test;

/**
 * Created by ZubovVP on 05.06.2018
 * zubovvp@yadndex.ru
 */
public class ThreadPoolTest {
    private ThreadPool tr;

    public ThreadPoolTest() {
        tr = new ThreadPool();
    }

    @Test
    public void work() {
        for (int x = 0; x < 11; x++) {
            tr.work(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + " Tread running");
                }
            });
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tr.shutdown();
    }

    @Test
    public void work2() throws InterruptedException {
        for (int x = 0; x < 11; x++) {
            tr.work(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " -" + " Tread running №1");
                }
            });
        }
        Thread.sleep(5000);

        for (int x = 0; x < 10; x++) {
            tr.work(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " -" + " Tread running №2");
                }
            });
        }
        Thread.sleep(5000);
        tr.shutdown();
    }
}
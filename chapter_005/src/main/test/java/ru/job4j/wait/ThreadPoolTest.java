package ru.job4j.wait;

import org.junit.Test;

/**
 * Created by ZubovVP on 05.06.2018
 * zubovvp@yadndex.ru
 */
public class ThreadPoolTest {
    ThreadPool tr = new ThreadPool();

    public ThreadPoolTest() throws InterruptedException {
    }

    @Test
    public void work() throws InterruptedException {
        for (int x = 0; x < 5; x++) {
            tr.work(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " - " + " Tread running");
                }
            });
        }
        Thread.sleep(5000);
        tr.shutdown();
    }


    @Test
    public void work2() throws InterruptedException {
        for (int x = 0; x < 5; x++) {
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

        for (int x = 0; x < 5; x++) {
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
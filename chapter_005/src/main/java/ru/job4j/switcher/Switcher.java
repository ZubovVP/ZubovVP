package ru.job4j.switcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Random;
import java.util.concurrent.Semaphore;


/**
 * Created by ZubovVP on 10.09.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class Switcher {
    @GuardedBy("this")
    private Semaphore semaphore = new Semaphore(1);
    private StringBuilder sb = new StringBuilder();
    private Random rd = new Random();
    private volatile boolean starting = true;

    public String getLine() {
        return this.sb.toString();
    }

    /**
     * Add element in the line.
     *
     * @param number - number.
     */
    public void add(int number) {
        sb.append(number);
    }

    private void addNumber(int number) {
        try {
            semaphore.acquire();
            Thread.sleep(200);

            for (int x = 0; x < 10; x++) {
                sb.append(number);
            }
            System.out.println(getLine());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    /**
     * Create 2 threads.
     * Threads alternately insert 10 elements in the line.
     */
    public void play() {
        Thread thread1 = new Thread(() -> {
            int number = rd.nextInt(10);
            while (starting) {
                addNumber(number);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            int number = rd.nextInt(10);
            while (starting) {
                addNumber(number);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
            }

        });
        thread1.start();
        thread2.start();
    }

    /**
     * Shutdown method play.
     */
    public void shutdown() {
        starting = false;
    }
}

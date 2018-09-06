package ru.job4j.wait;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ZubovVP on 05.09.2018
 * zubovvp@yadndex.ru
 */
public class DeadLock {
    private CountDownLatch count = new CountDownLatch(2);
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private Thread th1;
    private Thread th2;

    public void start() {
        th1 = new Thread(() -> {
            lock1.lock();
            count.countDown();
            try {
                count.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();
        });

        th2 = new Thread(() -> {
            lock2.lock();
            count.countDown();
            try {
                count.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1.lock();
        });
        th1.start();
        th2.start();
    }

    /**
     * Check are live threads.
     *
     * @return
     */
    public boolean areAliveThreads() {
        return th1.isAlive() && th2.isAlive();
    }
}
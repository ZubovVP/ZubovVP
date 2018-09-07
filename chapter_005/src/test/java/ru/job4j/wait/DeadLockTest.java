package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by ZubovVP on 06.09.2018
 * zubovvp@yadndex.ru
 */
public class DeadLockTest {
    private DeadLock d1 = new DeadLock();

    @Test
    public void start() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                d1.start();
            }
        });
        thread.start();

        for (int x = 0; x < 5; x++) {
            Thread.sleep(1000);
            assertTrue(d1.areAliveThreads());
        }
    }
}
package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests of the MyLock
 * Created by ZubovVP on 13.06.2018
 * zubovvp@yadndex.ru
 */
public class MyLockTest {
    private MyLock testLock = new MyLock();
    private int count;

    /**
     * Increment the count.
     * @throws InterruptedException
     */
    private void rightIncrement() throws InterruptedException {
        testLock.lock();
        count++;
        testLock.unlock();
    }

    private void wrongIncrement() throws InterruptedException {
        count++;
    }

    @Test
    public void useMyLockShouldRightCount() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 1_000_000; x++) {
                    try {
                        rightIncrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 1_000_000; x++) {
                    try {
                        rightIncrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
//      System.out.printf("Count = %d%n", count);
        assertThat(this.count, is(2_000_000));
    }
    @Test
    public void useMyLockShouldWrongCount() throws InterruptedException {
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 1_000_000; x++) {
                    try {
                        wrongIncrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 1_000_000; x++) {
                    try {
                        wrongIncrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
//      System.out.printf("Count = %d%n", count);
        assertTrue(this.count < 200000000);
    }
}
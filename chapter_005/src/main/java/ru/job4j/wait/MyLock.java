package ru.job4j.wait;

/**
 * Created by ZubovVP on 08.06.2018
 * zubovvp@yadndex.ru
 */
public class MyLock {
    private boolean work = true;

    /**
     * Lock the place.
     *
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        while (!work) {
            wait();
        }
        this.work = false;

    }

    /**
     * Unlock the place.
     */
    public synchronized void unlock() {
        this.work = true;
        notify();
    }
}
package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 11.02.2021.
 */
@ThreadSafe
public class CountBarrier {@GuardedBy("this")
    private final Object monitor = this;

    private int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (this.monitor) {
            count += 1;
            this.monitor.notify();
        }

    }

    public void await() {
        synchronized (this.monitor) {
            if (total != count) {
                System.out.println(Thread.currentThread().getName() + " wait....");
                try {
                    this.monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier cb = new CountBarrier(3);
        Thread thread1 = new Thread(cb::await);
        Thread thread2 = new Thread(cb::await);
        Thread thread3 = new Thread(cb::await);
        Thread thread4 = new Thread(() -> {
            cb.count();
            cb.count();
            cb.count();
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}



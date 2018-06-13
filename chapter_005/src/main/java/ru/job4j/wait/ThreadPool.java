package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ZubovVP on 04.06.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class ThreadPool {
    @GuardedBy("lock")
    private final Object lock = new Object();
    private final List<Thread> treads;
    private Queue<Runnable> tasks;
    private volatile boolean running = true;

    /**
     * Constructor.
     *
     * @throws InterruptedException
     */
    public ThreadPool() throws InterruptedException {
        this.treads = new LinkedList<>();
        this.tasks = new LinkedBlockingQueue<>();
        execute(Runtime.getRuntime().availableProcessors());
    }

    private void execute(int size) throws InterruptedException {
        for (int x = 0; x < size; x++) {
            treads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable nextTask;
                    while (running) {
                        nextTask = tasks.poll();
                        synchronized (lock) {
                            if (nextTask != null) {
                                nextTask.run();
                            } else {
                                try {
                                    System.out.println(Thread.currentThread().getName() + " - waiting");
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }));
        }
        for (Thread thr : this.treads) {
            thr.start();
        }
    }

    /**
     * Add job in the tasks.
     *
     * @param job - job
     */
    public void work(Runnable job) {
        this.tasks.offer(job);
        synchronized (this.lock) {
            this.lock.notify();
        }
    }

    /**
     * Finish work.
     */
    public void shutdown() {
        synchronized (this.lock) {
            this.running = false;
            this.lock.notify();
        }
    }
}

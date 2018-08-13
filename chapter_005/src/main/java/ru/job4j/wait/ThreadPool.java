package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZubovVP on 04.06.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class ThreadPool {
    private final List<Thread> treads;
    @GuardedBy("this")
    private BlockingQueue<Runnable> tasks;
    private volatile boolean running = true;

    /**
     * Constructor.
     */
    public ThreadPool() {
        this.treads = new LinkedList<>();
        this.tasks = new LinkedBlockingQueue<>();
    }

    private void execute(int size) {
        for (int x = 0; x < size; x++) {
            treads.add(new Thread(() -> {
                Runnable nextTask;
                while (running) {
                    try {
                        nextTask = tasks.poll(1000, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "- finish.");
                        break;
                    }
                    if (nextTask != null) {
                        nextTask.run();
                    } else {
                        System.out.println(Thread.currentThread().getName() + " - waiting.");
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
        if (treads.isEmpty()) {
            execute(Runtime.getRuntime().availableProcessors());
        }
        this.tasks.offer(job);
    }

    /**
     * Finish work.
     */
    public void shutdown() {
        this.running = false;
        for (Thread thread : this.treads) {
            if (!thread.isInterrupted()) {
                thread.interrupt();
            }
        }
    }
}

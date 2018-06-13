package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ZubovVP on 03.06.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class SimpleBlokingQueue<T> {
    @GuardedBy("lock")
    private Queue<T> queue;
    private final int limit;
    private final Object lock = new Object();

    /**
     * Constructor.
     *
     * @param size - size queue.
     */
    public SimpleBlokingQueue(int size) {
        this.queue = new LinkedList<T>();
        this.limit = size;
    }

    /**
     * Add value in queue.
     *
     * @param value - value.
     * @throws InterruptedException
     */
    public void offer(T value) throws InterruptedException {
        synchronized (this.lock) {
            while (queue.size() == limit) {
                this.lock.wait();
            }
            queue.offer(value);
            this.lock.notify();
        }
    }

    /**
     * Get element from queue.
     *
     * @return - element.
     * @throws InterruptedException
     */
    public T poll() throws InterruptedException {

        synchronized (this.lock) {
            while (queue.isEmpty()) {
                this.lock.wait();
            }
            this.lock.notify();
            return queue.poll();
        }
    }
}

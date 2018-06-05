package ru.job4j.wait;

/**
 * Created by ZubovVP on 03.06.2018
 * zubovvp@yadndex.ru
 */
public class ProducerConsumer<T> {
    private final SimpleBlokingQueue<T> queue;


    /**
     * Constructor.
     *
     * @param size - size of SimpleBlokingQueue.
     */
    public ProducerConsumer(int size) {
        this.queue = new SimpleBlokingQueue<>(size);
    }

    /**
     * Producer.
     *
     * @param count - count.
     * @throws InterruptedException
     */
    public void produce(T count) throws InterruptedException {
        this.queue.offer(count);
    }

    /**
     * Consumer.
     *
     * @return - count
     * @throws InterruptedException
     */
    public T consume() throws InterruptedException {
        return this.queue.poll();
    }
}


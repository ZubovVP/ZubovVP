package ru.job4j.blocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.02.2021.
 */
@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer value;
        Integer next;
        do {
            value = count.get();
            next = value + 1;
        } while (!this.count.compareAndSet(value, next));
    }

    public int get() {
        return count.get();
    }
}

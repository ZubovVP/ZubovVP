package ru.job4j.connecrion;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.04.2020.
 */
@ThreadSafe
public class Cache {
    private static Cache ourInstance = new Cache();
    @GuardedBy("cache")
    private ArrayBlockingQueue<Element> cache = new ArrayBlockingQueue<>(200, true);

    public static Cache getInstance() {
        return ourInstance;
    }

    private Cache() {
    }

    public Element get() throws InterruptedException {
        return this.cache.poll(1, TimeUnit.SECONDS);
    }

    public boolean add(Element element) throws InterruptedException {
        return this.cache.offer(element, 1, TimeUnit.SECONDS);
    }

    public int size() {
        return this.cache.size();
    }

    public void removeCache() {
        this.cache.clear();
    }
}

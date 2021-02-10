package ru.job4j.synchro;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.02.2021.
 */
public class SingleLockListTest {

    @Test
    public void add100ElementsToCollectionUseTwoThreadsShould100() throws InterruptedException {
        AtomicInteger count = new AtomicInteger();
        SingleLockList<Integer> list = new SingleLockList<>(120);
        Thread thread1 = new Thread(() -> {
            for (int x = 0; x < 50; x++) {
                list.add(count.getAndIncrement());
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int x = 0; x < 50; x++) {
                list.add(count.getAndIncrement());
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertThat(list.size(), is(100));
    }

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>();
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        assertThat(rsl, is(Set.of(1, 2)));
    }
}
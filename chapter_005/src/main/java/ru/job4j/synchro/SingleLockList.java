package ru.job4j.synchro;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.MyArrayList;

import java.util.Iterator;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.02.2021.
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final MyArrayList<T> list;

    public SingleLockList(int capacity) {
        this.list = new MyArrayList<>(capacity);
    }

    public SingleLockList() {
        this.list = new MyArrayList<>();
    }

    public void add(T value) {
        synchronized (this) {
            this.list.add(value);
        }
    }

    public T get(int index) {
        synchronized (this) {
            return this.list.get(index);
        }
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private MyArrayList copy(MyArrayList<T> list) {
        MyArrayList result = null;
        try {
            result = list.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public synchronized int size() {
        return this.list.size();
    }
}

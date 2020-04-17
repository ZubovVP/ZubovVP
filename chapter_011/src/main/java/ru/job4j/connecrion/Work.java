package ru.job4j.connecrion;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.04.2020.
 */
public class Work implements Runnable {
    private Cache cache;
    private GroupAble group;
    private ReadAble reader;

    public Work(Cache cache, GroupAble group, ReadAble reader) {
        this.cache = cache;
        this.group = group;
        this.reader = reader;
    }

    @Override
    public void run() {
        while (reader.readNow() || cache.size() > 0) {
            try {
                this.group.group(this.cache.get());
                // System.out.println(Thread.currentThread().getName() + " working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " OFF");
    }
}

package ru.job4j.connecrion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.04.2020.
 */
public class Start {
    private GroupAble group;
    private ReadAble reader;
    private Cache cache;

    public Start(GroupAble group, ReadAble reader, Cache cache) {
        this.group = group;
        this.reader = reader;
        this.cache = cache;
    }

    public void start(int nThreads) {
        this.reader.startRead();
        createPool(nThreads);
    }

    private void createPool(int nThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        while (this.cache.size() == 0) {
        }
        for (int x = 0; x < nThreads; x++) {
            executorService.submit(new Work(this.cache, this.group, this.reader));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = String.format("%s%s", System.getProperty("user.dir"), "\\chapter_011\\lng.csv");
        Reader reader = new Reader(source, Cache.getInstance());
        Start start = new Start(new Group(StorageElement.getInstance()),reader , Cache.getInstance());
        start.start(Runtime.getRuntime().availableProcessors() * 8);
        System.out.println(String.format("Обработано элементов из 1 000 000 за 30 секунд - %s штук \nCоздано групп - %s", reader.getReadElements(), StorageElement.getInstance().getStorage().size()));
    }
}

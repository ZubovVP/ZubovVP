package ru.job4j.blocking;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 04.07.2018
 * zubovvp@yadndex.ru
 */
public class NonBlockingCacheTest {
    private NonBlockingCache test1 = new NonBlockingCache();

    @Test
    public void addObjectsSomeThreadsShouldRightAnswer() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int x = 0; x < 5000; x++) {
            int finalX = x;
            executorService.submit(() -> {
                String name = "TestName" + finalX;
                test1.add(new Base(name));
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        assertThat(test1.getStore().size(), is(5000));
    }

    @Test
    public void deleteObjectsSomeThreadsShouldRightAnswer() throws InterruptedException {
        ArrayList<Base> list = new ArrayList<>();
        Thread thread1 = new Thread(() -> {
            for (int x = 0; x < 5000; x++) {
                String name = "TestName" + x;
                Base base = new Base(name);
                list.add(base);
                test1.add(base);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int x = 0; x < 4000; x++) {
                test1.delete(list.get(x));
            }
        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(test1.getStore().size(), is(1000));
    }

    @Test
    public void updateObjectOneThreadShouldCorrectAnswer() throws InterruptedException {
        Base base = new Base("test1");
        test1.add(base);
//        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
//        Base base1 = new Base("test2");
//        while (!Thread.currentThread().isInterrupted()) {
//            executorService2.submit(() -> {
//                base1.setId(base.getId());
//                test1.update(base1);
//            });
//        }
//        executorService2.shutdown();
//        executorService2.awaitTermination(0,5, TimeUnit.SECONDS);

        Thread thread3 = new Thread(() -> {
            Base base1 = new Base("test2");
            while (!Thread.currentThread().isInterrupted()) {
                base1.setId(base.getId());
                test1.update(base1);
                //Check version object
               // System.out.printf("Version - %d%n", test1.getStore().get(base.getId()).getVersion());
            }
        });
        thread3.start();
        Thread.sleep(500);
    }

    @Ignore
    public void updateObjectTwoThreadsShouldException() throws InterruptedException {
        Base base = new Base("test1");
        test1.add(base);
//        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
//        Base base1 = new Base("test2");
//        while (!Thread.currentThread().isInterrupted()) {
//            executorService2.submit(() -> {
//                base1.setId(base.getId());
//                test1.update(base1);
//            });
//        }
//        executorService2.shutdown();
//        executorService2.awaitTermination(0,5, TimeUnit.SECONDS);

        Thread thread5 = new Thread(() -> {
            Base base1 = new Base("test2");
            while (!Thread.currentThread().isInterrupted()) {
                base1.setId(base.getId());
                test1.update(base1);
                //Check version object
                //  System.out.printf("Thread5 - Version - %d%n", test1.getStore().get(base.getId()).getVersion());
            }
        });
        Thread thread6 = new Thread(() -> {
            Base base1 = new Base("test2");
            while (!Thread.currentThread().isInterrupted()) {
                base1.setId(base.getId());
                test1.update(base1);
                //Check version object
                // System.out.printf("Thread6 - Version - %d%n", test1.getStore().get(base.getId()).getVersion());
            }
        });
        thread5.start();
        thread6.start();
        Thread.sleep(500);
    }
}
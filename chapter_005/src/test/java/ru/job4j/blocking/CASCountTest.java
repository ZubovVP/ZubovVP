package ru.job4j.blocking;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.02.2021.
 */
public class CASCountTest {
    private final CASCount casCount = new CASCount();

    @Test
    public void useFourThreadsAndTestIncrement() throws InterruptedException {
        Runnable runnable = () -> {
            for (int x = 0; x < 1000; x++) {
                this.casCount.increment();
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        Thread.sleep(25);
        Assert.assertThat(this.casCount.get(), is(4000));
    }

}
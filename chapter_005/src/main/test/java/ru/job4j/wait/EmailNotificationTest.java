package ru.job4j.wait;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 31.07.2018
 * zubovvp@yadndex.ru
 */
public class EmailNotificationTest {
    BlockingQueue<User> users = new LinkedBlockingDeque<>();
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private EmailNotification em = new EmailNotification();

    @Test
    public void testEmailTo() throws InterruptedException {
        users.addAll(Arrays.asList(new User("Duke", "duke@yandex.ru"), new User("Kate", "kate@yandex.ru"), new User("Tom", "tom@google.com")));

        for (int x = 0; x < users.size(); x++) {
            this.pool.submit(new Runnable() {
                @Override
                public void run() {
                    User us = null;
                    try {
                        us = users.poll(1000, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (us != null) {
                        em.emailTo(us);
                    }
                }
            });
        }
        this.pool.shutdown();
        this.pool.awaitTermination(1, TimeUnit.DAYS);
        assertThat(users.size(), is(0));
    }

}
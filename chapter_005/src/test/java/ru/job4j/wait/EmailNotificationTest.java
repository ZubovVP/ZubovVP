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

    @Test
    public void testEmailTo() throws InterruptedException {
        this.users.addAll(Arrays.asList(new User("Duke", "duke@yandex.ru"), new User("Kate", "kate@yandex.ru"), new User("Tom", "tom@google.com")));
        EmailNotification em = new EmailNotification(this.users);
        em.start();
        Thread.sleep(200);
        assertThat(em.getUsers().size(), is(0));
    }
}
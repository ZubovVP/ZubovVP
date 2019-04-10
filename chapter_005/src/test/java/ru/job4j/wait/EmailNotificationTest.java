package ru.job4j.wait;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void testEmailTo() throws InterruptedException {
        this.users.addAll(Arrays.asList(new User("Duke", "duke@yandex.ru"), new User("Kate", "kate@yandex.ru"), new User("Tom", "tom@google.com")));
        EmailNotification em = new EmailNotification(this.users);
        em.start();
        em.close();
        MatcherAssert.assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("The poll is empty").append(System.lineSeparator())
                .toString()));
    }
}
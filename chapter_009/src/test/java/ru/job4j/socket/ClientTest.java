package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 18.09.2019.
 */
public class ClientTest {
    private final String lineSeparator = System.getProperty("line.separator");

    @Test
    public void testaawqw() {
        test("exit", String.format("hello%sexit%s", this.lineSeparator, this.lineSeparator));
    }

    @Test
    public void testaawqw1() {
        test(Joiner.on(this.lineSeparator).join("test", "exit"), Joiner.on(this.lineSeparator).join("hello", "test", "exit", ""));

    }

    private void test(String test, String result) {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream((test).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Client server = new Client(socket);
        server.start();
        assertThat(out.toString(), is(result));
    }
}
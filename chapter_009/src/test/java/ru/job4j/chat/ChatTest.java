package ru.job4j.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 28.08.2019.
 */
public class ChatTest {
    private ByteArrayOutputStream out;
    private final PrintStream stdout = System.out;
    private String way = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\java\\ru\\job4j\\chat\\phrases.txt");


    @After
    public void backOutput() throws IOException {
        System.setOut(this.stdout);
        this.out.close();
    }


    @Before
    public void prepareOutputStream() {
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
    }

    @Test
    public void testFinish() {
        StringBuilder sb = new StringBuilder();
        sb.append("Привет\n");
        sb.append("Как дела?\n");
        sb.append("закончить\n");
        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        new Chat().start();
        String a = new String(out.toByteArray());
        String[] b = a.split("\r\n");
        assertThat(b.length, is(2));
        try (
                BufferedReader reader = new BufferedReader(new FileReader(this.way))) {
            List<String> phrases = new ArrayList<>();
            String pharse = reader.readLine();
            while (pharse != null) {
                phrases.add(pharse);
                pharse = reader.readLine();
            }
            for (String ex : b) {
                assertTrue(phrases.contains(ex));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStop() {
        StringBuilder sb = new StringBuilder();
        sb.append("закончить\n");
        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        new Chat().start();
        String a = new String(out.toByteArray());
        String[] b = a.split("\r\n");
        assertThat(b.length, is(1));
        assertThat(b[0], is(""));

    }

    @Test
    public void testStop2() {
        StringBuilder sb = new StringBuilder();
        sb.append("стоп\n");
        sb.append("Как дела?\n");
        sb.append("Как день прошёл?\n");
        sb.append("Что делаешь?\n");
        sb.append("закончить\n");
        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        new Chat().start();
        String a = new String(out.toByteArray());
        String[] b = a.split("\r\n");
        assertThat(b.length, is(1));
        assertThat(b[0], is(""));
    }

    @Test
    public void commonTest() {
        StringBuilder sb = new StringBuilder();
        sb.append("Привет\n");
        sb.append("Как дела?\n");
        sb.append("Как день прошёл?\n");
        sb.append("Что делаешь?\n");
        sb.append("закончить\n");
        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        new Chat().start();
        String a = new String(out.toByteArray());
        String[] b = a.split("\r\n");
        assertThat(b.length, is(4));
        try (
                BufferedReader reader = new BufferedReader(new FileReader(this.way))) {
            List<String> phrases = new ArrayList<>();
            String pharse = reader.readLine();
            while (pharse != null) {
                phrases.add(pharse);
                pharse = reader.readLine();
            }
            for (String ex : b) {
                assertTrue(phrases.contains(ex));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStart() {
        StringBuilder sb = new StringBuilder();
        sb.append("стоп\n");
        sb.append("Как дела?\n");
        sb.append("Как день прошёл?\n");
        sb.append("старт\n");
        sb.append("Что делаешь?\n");
        sb.append("закончить\n");
        String data = sb.toString();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        System.setIn(is);
        new Chat().start();
        String a = new String(out.toByteArray());
        String[] b = a.split("\r\n");
        assertThat(b.length, is(2));
        try (
                BufferedReader reader = new BufferedReader(new FileReader(this.way))) {
            List<String> phrases = new ArrayList<>();
            String pharse = reader.readLine();
            while (pharse != null) {
                phrases.add(pharse);
                pharse = reader.readLine();
            }
            for (String ex : b) {
                assertTrue(phrases.contains(ex));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
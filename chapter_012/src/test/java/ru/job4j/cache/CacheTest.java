package ru.job4j.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 11.03.2020.
 */
public class CacheTest {
    private File correctFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\ru\\job4j\\cache\\text.txt");
    private Cache<String> cache = new Cache<>(System.getProperty("user.dir") + "\\src\\test\\java\\ru\\job4j\\cache");


    @Before
    public void start() {
        createFile();
    }

    @After
    public void finish() {
        this.correctFile.delete();
    }

    @Test
    public void getTextOfCorrectFile() throws IOException {
        assertThat(this.cache.getText("text.txt"), is("Test"));
    }

    @Test
    public void getTextOfNotExistFile() {
        try {
            this.cache.getText("aaa.txt");
            fail("Expected IOException");
        } catch (IOException io) {
            assertThat(io.getMessage(), is("Error find or read file, Name file = aaa.txt."));
        }
    }

    @Test
    public void testSizeCache() throws IOException {
        assertThat(this.cache.getSize(), is(0));
        this.cache.getText("text.txt");
        assertThat(this.cache.getSize(), is(1));
        this.cache.getText("text.txt");
        assertThat(this.cache.getSize(), is(1));
    }

    private void createFile() {
        StringBuilder sb = new StringBuilder();
        sb.append("Test");
        try (FileWriter writer = new FileWriter(correctFile, false)) {
            this.correctFile.createNewFile();
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
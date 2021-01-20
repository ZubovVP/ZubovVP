package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.01.2021.
 */
public class LogFilterTest {
    private File file = new File("./404.txt");


    @Before
    public void start() {
        if (this.file.exists()) {
            this.file.deleteOnExit();
        }
    }

    @After
    public void finish() {
        if (this.file.exists()) {
            this.file.deleteOnExit();
        }
    }


    @Test
    public void testFilter() {
        List<String> result = LogFilter.filter("log.txt");
        for (String line : result) {
            assertTrue(line.contains("404"));
        }
    }

    @Test
    public void testSave() {
        List<String> log = LogFilter.filter("log.txt");
        assertFalse(this.file.exists());
        LogFilter.save(log, this.file.getAbsolutePath());
        assertTrue(this.file.exists());
    }
}
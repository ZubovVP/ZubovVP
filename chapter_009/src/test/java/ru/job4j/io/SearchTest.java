package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.08.2019.
 */
public class SearchTest {
    String folder = "Test";

    @Before
    public void setUp() throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir"), folder);
        file.mkdir();
        for (int x = 0; x < 5; x++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Test");
            sb.append("\\");
            sb.append(x);
            File dir = new File(System.getProperty("java.io.tmpdir"), sb.toString());
            dir.mkdir();
            sb.append("\\Text");
            sb.append(x);
            sb.append(".txt");
            dir = new File(System.getProperty("java.io.tmpdir"), sb.toString());
            dir.createNewFile();
            if (x % 2 == 1) {
                sb = new StringBuilder();
                sb.append("Test");
                sb.append("\\");
                sb.append(x);
                sb.append("\\Text");
                sb.append(x);
                sb.append(".xml");
                dir = new File(System.getProperty("java.io.tmpdir"), sb.toString());
                dir.createNewFile();
            }
            if (x % 3 == 0) {
                sb = new StringBuilder();
                sb.append("Test");
                sb.append("\\");
                sb.append(x);
                sb.append("\\Text");
                sb.append(x);
                sb.append(".word");
                dir = new File(System.getProperty("java.io.tmpdir"), sb.toString());
                dir.createNewFile();
            }
        }
    }

    @After
    public void tearDown() {
        File file = new File(System.getProperty("java.io.tmpdir"), folder);
        deleteDirectory(file);
    }

    @Test
    public void findTest() {
        File file = new File(System.getProperty("java.io.tmpdir"), folder);
        Search search = new Search();
        List<String> exts = new ArrayList<>();
        exts.add("xml");
        assertThat(search.find(file.getAbsolutePath(), exts).size(), is(2));
        exts.add("txt");
        assertThat(search.find(file.getAbsolutePath(), exts).size(), is(7));
        exts.remove("xml");
        assertThat(search.find(file.getAbsolutePath(), exts).size(), is(5));
        exts.add("word");
        assertThat(search.find(file.getAbsolutePath(), exts).size(), is(7));
        exts.add("xml");
        assertThat(search.find(file.getAbsolutePath(), exts).size(), is(9));
        exts.clear();
        assertThat(search.find(file.getAbsolutePath(), exts).size(), is(0));
    }


    private static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }
}

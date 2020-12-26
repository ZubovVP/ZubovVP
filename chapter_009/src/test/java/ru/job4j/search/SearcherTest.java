package ru.job4j.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.10.2019.
 */
public class SearcherTest {
    private final File directory = new File(String.format("%s%s%s", new File("").getAbsolutePath(), File.separator, "test"));
    private final File file = new File(String.format("%s%s%s", new File("").getAbsolutePath(), File.separator, "TestSearcher.txt"));
    private final String source = new File("").getAbsolutePath();
    private final String target = this.directory.getPath();

    @Before
    public void createFile() throws IOException {
        this.directory.mkdir();
        this.file.createNewFile();
        try (FileWriter writer = new FileWriter(file.getPath())) {
            writer.write("Test!");
        }
    }

    @After
    public void deleteFile() throws IOException {
        this.file.delete();
        this.directory.delete();
    }

    @Test
    public void testFullName() throws Exception {
        String nameFile = "TestSearcher.txt";
        this.start(this.source, nameFile, this.target);
        File file = new File(target + File.separator + nameFile);
        this.check(file);
        file.delete();
    }

    @Test
    public void testWithMask() throws Exception {
        String nameFile = "TestSea*.txt";
        this.start(this.source, nameFile, this.target);
        File file = new File(this.target + File.separator + "TestSearcher.txt");
        this.check(file);
        file.delete();
    }

    @Test
    public void testWithMask2() throws Exception {
        String nameFile = "T?s?S?a*.txt";
        this.start(this.source, nameFile, this.target);
        File file = new File(target + File.separator + "TestSearcher.txt");
        this.check(file);
        file.delete();
    }

    @Test
    public void testWithMaskExt() throws Exception {
        String nameFile = "TestSearcher.???";
        this.start(this.source, nameFile, this.target);
        File file = new File(target + File.separator + "TestSearcher.txt");
        this.check(file);
        file.delete();
    }

    @Test
    public void testWithMaskExt2() throws Exception {
        String nameFile = "TestSearcher.*";
        this.start(this.source, nameFile, this.target);
        File file = new File(target + File.separator + "TestSearcher.txt");
        this.check(file);
        file.delete();
    }

    @Test(expected = Exception.class)
    public void nullDerectory() throws Exception {
        String nameFile = "T?s?S?a*.txt";
        this.start(null, nameFile, this.target);
    }

    @Test(expected = Exception.class)
    public void nullSource() throws Exception {
        String nameFile = "T?s?S?a*.txt";
        this.start(this.source, nameFile, null);
    }

    private void start(String source, String nameFile, String target) throws Exception {
        String[] arguments = new String[]{"java", "-jar", "find.jar", "-d", source, "-n", nameFile, "-o", target};
        Searcher.main(arguments);
    }

    private void check(File target) {
        assertTrue(this.directory.exists());
        assertTrue(target.exists());
        try (FileReader fileReader = new FileReader(target);
             BufferedReader reader = new BufferedReader(fileReader)) {
            assertThat(reader.readLine(), is("Test!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
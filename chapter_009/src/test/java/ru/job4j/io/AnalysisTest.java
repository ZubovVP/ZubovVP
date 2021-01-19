package ru.job4j.io;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.08.2019.
 */
public class AnalysisTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File source;
    private File target;

    @Before
    public void start() throws IOException {
        this.source = this.folder.newFile("file.txt");
        this.target = this.folder.newFile("result.txt");
        StringBuilder sb = new StringBuilder();
        sb.append("200 10:56:01\n").append("\n");
        sb.append("500 10:57:01\n").append("\n");
        sb.append("400 10:58:01\n").append("\n");
        sb.append("200 10:59:01\n").append("\n");
        sb.append("500 11:01:02\n").append("\n");
        sb.append("200 11:02:02\n").append("\n");
        try (FileWriter writer = new FileWriter(source, false)) {
            this.source.createNewFile();
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unavailable() {
        Analysis analizy = new Analysis();
        analizy.unavailable(this.source.getAbsolutePath(), this.target.getAbsolutePath());
        try (
                FileReader fr = new FileReader(this.target);
                BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            assertThat(line, is("Start - Finish:"));
            line = reader.readLine();
            assertThat(line, is("10:57:01 - 10:59:01"));
            line = reader.readLine();
            assertThat(line, is("11:01:02 - 11:02:02"));
            line = reader.readLine();
            assertNull(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
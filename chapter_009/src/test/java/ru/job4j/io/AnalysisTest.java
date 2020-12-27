package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
    private File source = new File(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", new File("").getAbsolutePath(), File.separator, "src", File.separator, "main", File.separator, "java", File.separator, "ru", File.separator, "job4j", File.separator, "io", File.separator, "file.txt"));
    private File target = new File(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", new File("").getAbsolutePath(), File.separator, "src", File.separator, "main", File.separator, "java", File.separator, "ru", File.separator, "job4j", File.separator, "io", File.separator, "result.txt"));

    @Ignore
    public void start() {
        StringBuilder sb = new StringBuilder();
        sb.append("200 10:56:01\n").append("\n");
        sb.append("500 10:57:01\n").append("\n");
        sb.append("400 10:58:01\n").append("\n");
        sb.append("200 10:59:01\n").append("\n");
        sb.append("500 11:01:02\n").append("\n");
        sb.append("200 11:02:02\n").append("\n");
        try (FileWriter writer = new FileWriter(source, false)) {
            if (this.source.exists()) {
                this.source.delete();
            }
            this.source.createNewFile();
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void finish() {
        this.target.delete();
        this.source.delete();
    }

    @Ignore
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
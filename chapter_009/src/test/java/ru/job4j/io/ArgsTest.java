package ru.job4j.io;

import org.junit.Test;


import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 05.09.2019.
 */
public class ArgsTest {

    @Test
    public void testArchive() {
        String source = System.getProperty("user.dir");

        String[] arguments = new String[]{"java", "-jar", "pack.jar", "-d", source, "-e", "*.java", "-o", "project.zip"};
        try {
            Args.main(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String way = String.format("%s%s%s", source, "\\", "project.zip");
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(way))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                assertFalse(entry.getName().endsWith("java"));
                zin.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(way).delete();
    }
}
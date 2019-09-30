package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.08.2019.
 */
public class Zip {
    private final Args args;
    private static String exclude;

    public Zip(Args args) {
        this.args = args;
    }

    /**
     * Assembly of all files and archive.
     *
     * @param source - way of the files.
     * @param target - target way.
     */
    public void pack(File source, File target) {
        List<File> files = this.args.start(source.getPath(), exclude);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File f : files) {
                zip.putNextEntry(new ZipEntry(f.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(f))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args - should have :
     *             1) source directory;
     *             2) exclude;
     *             3) target.
     * @throws Exception - Exception.
     */
    public static void main(String[] args) throws Exception {
        String directory = null;
        String output = null;
        for (int x = 0; x < args.length; x++) {
            if (args[x].equals("-d")) {
                directory = args[x + 1];
            } else if (args[x].equals("-e")) {
                exclude = args[x + 1].split("\\.")[1];
            } else if (args[x].equals("-o")) {
                output = args[x + 1];
            }
        }
        if (directory != null && output != null) {
            new Zip(new Args()).pack(new File(directory), new File(output));
        } else {
            throw new Exception("Parameters source or target not found");
        }
    }
}
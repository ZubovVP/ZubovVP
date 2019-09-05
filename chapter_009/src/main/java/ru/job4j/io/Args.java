package ru.job4j.io;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.08.2019.
 */
public class Args {
    private final ArrayBlockingQueue<File> files = new ArrayBlockingQueue<>(50);
    private final List<File> finalList = new ArrayList<>();
    private boolean flag = true;

    /**
     * Create 2 threads.
     * 1) - find fiels and add them in the collection(files).
     * 2) - check exclude all fiels on the collection(files), if exclude != endsWith of file, than add in the collection(finalList).
     * @param args - should have :
     *             1) source directory;
     *             2) exclude;
     *             3) target eay.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String directory = null;
        String exclude = null;
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
        if (directory != null && exclude != null && output != null) {
            Args arg = new Args();
            String finalDirectory = directory;
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    arg.directory(finalDirectory);
                }
            });
            thread1.start();

            String finalExclude = exclude;
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    arg.excule(finalExclude);
                }
            });
            thread2.start();
            thread1.join();
            thread2.join();
            arg.output(output);
        } else {
            throw new Exception("Parameters not found");
        }
    }

    /**
     * Call the method find. After method find, change flag.
     *
     * @param directory - source directory.
     */
    private void directory(String directory) {
        find(directory);
        this.flag = false;
    }

    /**
     * Check endsWith of the file, if endsWith of the file not equals excule than add in the finalList.
     *
     * @param excule -excule.
     */
    private void excule(String excule) {
        File file;
        while (flag || this.files.size() != 0) {
            file = this.files.poll();
            if (file != null && !file.getName().endsWith(excule)) {
                finalList.add(file);
            }
        }
    }

    /**
     * Call method pack.
     *
     * @param output - target way.
     */
    private void output(String output) {
        String way = String.format("%s%s%s", System.getProperty("user.dir"), "\\", output);
        new Zip().pack(finalList, new File(way));
    }

    /**
     * Check file< if it is a file than add in the collection, else if is a directory call a method find.
     *
     * @param directory - source way.
     */
    private void find(String directory) {
        File root = new File(directory);
        File[] files = root.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                this.files.add(file);
            } else {
                find(file.getPath());
            }
        }
    }
}
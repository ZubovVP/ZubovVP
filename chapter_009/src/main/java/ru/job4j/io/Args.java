package ru.job4j.io;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 19.08.2019.
 */
public class Args {
    private final List<File> files = new ArrayList<>();
    private List<File> finalList = new ArrayList<>();

    public List<File> start(String directory, String excule) {
        directory(directory);
        if (excule != null) {
            excule(excule);
        } else {
            this.finalList = this.files;
        }
        return this.output();
    }


    /**
     * Call the method find.
     *
     * @param directory - source directory.
     */
    private void directory(String directory) {
        find(directory);
    }

    /**
     * Check endsWith of the file, if endsWith of the file not equals excule than add in the finalList.
     *
     * @param excule - excule.
     */
    private void excule(String excule) {
        for (File file : this.files) {
            if (file != null && !file.getName().endsWith(excule)) {
                this.finalList.add(file);
            }
        }
    }

    /**
     * Return final list.
     */
    private List<File> output() {
        return this.finalList;
    }

    /**
     * Check file if it is a file than add in the collection, else if is a directory call a method find.
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
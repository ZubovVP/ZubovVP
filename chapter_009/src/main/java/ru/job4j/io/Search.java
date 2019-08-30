package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.08.2019.
 */
public class Search {

    /**
     * Find document.
     *
     * @param parent - link.
     * @return - list of a documents.
     */
    public List<File> find(String parent) {
        List<File> result = new ArrayList<>();
        File root = new File(parent);
        File[] files = root.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                result.add(file);
            } else {
                List<File> fileList = find(file.getPath());
                result.addAll(fileList);
            }
        }
        return result;
    }

    /**
     * Check exts of files.
     *
     * @param files - files.
     * @param exts  - exts.
     * @return - collection.
     */
    public List<File> check(List<File> files, List<String> exts) {
        List<File> result = new ArrayList<>();
        for (File file : files) {
            String ext = String.valueOf(file.getAbsolutePath().split("\\.")[1]);
            if (exts.contains(ext)) {
                result.add(file);
            }
        }
        return result;
    }
}

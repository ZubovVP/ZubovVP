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
     * @param exts   - file extensionsю
     * @return - list of a documents.
     */
    public List<File> find(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        File root = new File(parent);
        File[] files = root.listFiles();
        for (File file : files) {
            if (!file.isDirectory()) {
                String lineSep = file.getName().split("\\.")[1];
                if (exts.contains(lineSep)) {
                    result.add(file);
                }
            } else {
                List<File> fileList = find(file.getPath(), exts);
                result.addAll(fileList);
            }
        }
        return result;
    }
}

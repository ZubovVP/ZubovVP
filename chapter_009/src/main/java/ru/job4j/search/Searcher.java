package ru.job4j.search;

import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.09.2019.
 */
public class Searcher {
    private Search search;

    public Searcher() {
        this.search = new Search();
    }

    public static void main(String[] args) throws Exception {
        String directory = null;
        String nameFile = null;
        String target = null;
        for (int x = 0; x < args.length; x++) {
            if (args[x].equals("-d")) {
                directory = args[x + 1];
            } else if (args[x].equals("-n")) {
                nameFile = args[x + 1];
            } else if (args[x].equals("-m")) {
                nameFile = args[x + 1];
            } else if (args[x].equals("-f")) {
                nameFile = args[x + 1];
            } else if (args[x].equals("-o")) {
                target = args[x + 1];
            }
        }
        if (directory != null && target != null && nameFile != null) {
            Searcher search = new Searcher();
            List<File> result = search.find(directory);
            result = search.filter(result, nameFile);
            search.createDirectory(result, target);
        } else {
            throw new Exception("Parameters source or target not found");
        }
    }

    /**
     * Find all files.
     *
     * @param directory - way of directory
     * @return - List of files.
     * @throws Exception - if list is empty.
     */
    private List<File> find(String directory) throws Exception {
        List<File> files = this.search.find(directory);
        if (files.size() == 0) {
            throw new Exception("List is empty");
        }
        return files;
    }

    /**
     * Select the right values.
     *
     * @param files - list of files.
     * @param name  - ext.
     * @return - list of correct values.
     */
    private List<File> filter(List<File> files, String name) {
        List<File> result = new ArrayList<>();
        char[] nameChar = name.split("\\.")[0].toCharArray();
        char[] extChar = name.split("\\.")[1].toCharArray();
        for (File file : files) {
            if (file.getName().lastIndexOf(".") != -1 && file.getName().lastIndexOf(".") != 0) {
                if (check(file.getName().split("\\.")[1].toCharArray(), extChar)) {

                    if (check(file.getName().split("\\.")[0].toCharArray(), nameChar)) {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }


    /**
     * Check the same nameFile with ext.
     *
     * @param nameFile - nameFile.
     * @param ext      - ext.
     * @return - result.
     */
    private boolean check(char[] nameFile, char[] ext) {
        boolean result = false;
        for (int x = 0; x < ext.length; x++) {
            if (ext[x] == '?' && x != nameFile.length - 1) {
                continue;
            }
            if ((x == ext.length - 1 && ext.length == nameFile.length) || ext[x] == '*') {
                result = true;
                break;
            }
            if (ext[x] != nameFile[x]) {
                break;
            }
        }
        return result;
    }

    /**
     * Create directory with files.
     *
     * @param files  - files.
     * @param target - target.
     */
    private void createDirectory(List<File> files, String target) {
        try {
            File source = new File(target);
            if (!source.exists()) {
                source.mkdir();
            }
            File newFile;
            for (File file : files) {
                newFile = new File(String.format("%s%s%s", target, "\\", file.getName()));
                Files.copy(file.toPath(), newFile.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

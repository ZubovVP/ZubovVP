package ru.job4j.io;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.01.2021.
 */
public class FindDuplicator implements FileVisitor<Path> {
    private Map<String, Long> files = new HashMap<>();
    private List<String> duplicate = new ArrayList<>();

    public List<String> findDuplicate(String root) {
        try {
            Files.walkFileTree(Paths.get(root), this);
        } catch (IOException e) {
        }
        return this.duplicate;
    }


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (this.files.containsKey(file.getFileName().toString())) {
            if (this.files.get(file.getFileName().toString()) == Files.size(file)) {
                this.duplicate.add(file.toString());
            }
        } else {
            this.files.put(file.getFileName().toString(), new File(String.valueOf(file)).length() / (1024 * 1024));
        }

        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}

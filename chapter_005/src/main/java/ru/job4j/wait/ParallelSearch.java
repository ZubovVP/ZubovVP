package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ZubovVP on 15.06.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedBlockingQueue<>();

    @GuardedBy("this")
    private final List<String> paths = new CopyOnWriteArrayList<>();
    private volatile boolean finish = true;

    /**
     * Constructor
     *
     * @param root - root.
     * @param text - text.
     * @param exts - exts.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }


    /**
     * Add file in the collection.
     *
     * @param file - file.
     */
    private void addFileInThePaths(String file) {
        this.paths.add(file);
    }

    /**
     * Start program(start two threads:
     * 1) search file
     * 2) read file and equals with the text).
     *
     * @throws InterruptedException
     */
    public void init() throws InterruptedException {
        Thread search = new Thread(new Runnable() {
            @Override
            public void run() {
                Path entry = Paths.get(root);
                try {
                    Files.walkFileTree(entry, new MyFileVisitor(exts, files));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish = false;
            }
        });
        search.start();

        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {
                File file = null;
                String line;
                String[] words;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while ((finish || !files.isEmpty())) {
                    try {
                        file = new File(files.poll());
                        BufferedReader bf = new BufferedReader(new FileReader(file));
                        while ((line = bf.readLine()) != null) {
                            words = line.split(" ");
                            for (String word : words) {
                                if (word.equals(text)) {
                                    addFileInThePaths(file.getPath());
                                    break;
                                }
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (files.isEmpty() && search.isAlive()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        read.start();
        read.join(10000);
    }


    /**
     * Return result.
     *
     * @return - result.
     */
    public List<String> result() {
        return this.paths;
    }

    /**
     * MyFileVisitor.
     */
    private class MyFileVisitor extends SimpleFileVisitor<Path> {
        private List<String> exts;
        private Queue<String> files;

        public MyFileVisitor(List<String> exts, Queue<String> files) {
            this.exts = exts;
            this.files = files;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            for (String ext : exts) {
                if (path.toString().endsWith("." + ext)) {
                    files.offer(path.toString());
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

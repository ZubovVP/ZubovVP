package ru.job4j.connecrion;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

import java.util.Scanner;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.04.2020.
 */
public class Reader implements ReadAble {
    private String source;
    private Cache aim;
    private int readElements = 0;
    private Thread read = new Thread(() -> this.read(this.source, this.aim));


    public Reader(String source, Cache aim) {
        this.source = source;
        this.aim = aim;
    }

    private boolean read(String source, Cache aim) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line = reader.readLine();
            Scanner scanner;
            int index = 1;
            BigInteger first = null;
            BigInteger second = null;
            BigInteger third = null;
            while (line != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String a = scanner.next().replace("\"", "");
                    a = a.equals("") ? "0" : a;
                    BigInteger data = new BigInteger(a);
                    if (index == 1) {
                        first = data;
                    } else if (index == 2) {
                        second = data;
                    } else if (index == 3) {
                        third = data;
                    }
                    index++;
                }
                if (aim.add(new Element(first, second, third))) {
                    line = reader.readLine();
                    this.readElements++;
                }
                index = 1;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean readNow() {
        return this.read.isAlive();
    }

    @Override
    public boolean startRead() {
        read.start();
        return true;
    }

    public int getReadElements() {
        return readElements;
    }
}

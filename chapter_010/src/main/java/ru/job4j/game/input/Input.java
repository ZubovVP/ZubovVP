package ru.job4j.game.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.02.2020.
 */
public class Input implements AutoCloseable {
    private InputStreamReader is = new InputStreamReader(System.in);
    private BufferedReader reader = new BufferedReader(is);

    /**
     * Read line from console and convert to string.
     *
     * @return - line.
     */
    public String read() {
        String line = null;
        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * Close InputStreamReader and BufferedReader.
     */
    @Override
    public void close() {
        try {
            this.reader.close();
            this.is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

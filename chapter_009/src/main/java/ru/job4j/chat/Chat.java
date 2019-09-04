package ru.job4j.chat;


import java.io.*;
import java.util.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.08.2019.
 */
public class Chat {
    private List<String> phrases = new ArrayList<>();
    private Random random = new Random();

    /**
     * Start chat.
     */
    public void start() {
        boolean flag = true;
        readPhrases();
        try (InputStreamReader is = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(is)) {
            String line = reader.readLine();
            while (!line.equals("закончить")) {
                if (line.equals("стоп")) {
                    flag = false;
                }
                if (flag || line.equals("старт")) {
                    int index = random.nextInt(phrases.size());
                    System.out.println(phrases.get(index));
                    flag = true;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load phrases from document.
     */
    private void readPhrases() {
        String way = String.format("%s%s", System.getProperty("user.dir"), "\\src\\main\\java\\ru\\job4j\\chat\\phrases.txt");
        try (
                FileReader fr = new FileReader(new File(way));
                BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();
            while (line != null) {
                this.phrases.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

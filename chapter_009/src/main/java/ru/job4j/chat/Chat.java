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
    private static final String STOP = "стоп";
    private static final String FINISH = "закончить";
    private static final String START = "старт";
    private File log;

    public Chat(File log) {
        this.log = log;
    }

    /**
     * Start chat.
     */
    public void start() {
        boolean flag = true;
        checkLog(this.log);
        readPhrases();
        try (InputStreamReader is = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(is);
             PrintWriter writer = new PrintWriter(this.log.getAbsolutePath())) {
            String line = reader.readLine();
            while (!line.equals(FINISH)) {
                writer.write("You : " + line + "\n");
                if (line.equals(STOP)) {
                    flag = false;
                }
                if (flag || line.equals(START)) {
                    int index = random.nextInt(phrases.size());
                    String answer = phrases.get(index);
                    System.out.println(answer);
                    writer.write("Bot : " + answer + "\n");
                    flag = true;
                }
                line = reader.readLine();
            }
            writer.write("You : " + line + "\n");
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

    /**
     * Check for file exsist.
     *
     * @param log - file.
     */
    private void checkLog(File log) {
        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

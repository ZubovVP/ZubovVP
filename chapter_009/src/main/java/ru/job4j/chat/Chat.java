package ru.job4j.chat;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.util.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.08.2019.
 */
@ThreadSafe
public class Chat {
    @GuardedBy("this")
    private List<String> phrases;
    private Random random = new Random();

    public void start() {
        boolean flag = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line = reader.readLine();
            while (!line.equals("закончить")) {
                if (line.equals("стоп")) {
                    flag = false;
                }
                if (flag || line.equals("старт")) {
                    int index = random.nextInt(phrases.size());
                    System.out.println(this.phrases.get(index));
                    flag = true;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        List<String> result = Collections.synchronizedList(new ArrayList<>());
        try (
                FileReader fr = new FileReader(new File("C:\\projects\\ZubovVP\\chapter_009\\src\\main\\java\\ru\\job4j\\chat\\phrases"));
                BufferedReader reader = new BufferedReader(fr)) {

            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.phrases = result;
    }
}

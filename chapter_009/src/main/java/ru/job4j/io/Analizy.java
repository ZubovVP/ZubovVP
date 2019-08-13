package ru.job4j.io;

import java.io.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.08.2019.
 */
public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try (
                FileReader fr = new FileReader(new File(source));
                BufferedReader reader = new BufferedReader(fr);
                FileWriter writer = new FileWriter(target, false)) {
            sb.append("Start - Finish:\n");
            String line = reader.readLine();
            boolean work = false;
            while (line != null) {
                String[] split = line.split(" ");
                if ((split[0].equals("400") || split[0].equals("500")) && !work) {
                    sb.append(split[1]).append(" - ");
                    work = true;
                } else if (work && split[0].equals("200")) {
                    work = false;
                    sb.append(split[1]).append("\n");
                }
                line = reader.readLine();
            }

            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
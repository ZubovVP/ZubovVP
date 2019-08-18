package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.08.2019.
 */
public class Analysis {

    /**
     * Separate actions.
     *
     * @param source - source of the file.
     * @param target - target link.
     */
    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        List<String> actions = new ArrayList<>();
        try (
                FileReader fr = new FileReader(new File(source));
                BufferedReader reader = new BufferedReader(fr)) {
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
                    actions.add(sb.toString());
                    sb.setLength(0);
                }
                line = reader.readLine();
            }
            write(actions, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write actions in a file
     *
     * @param actions - actions.
     * @param target  - link.
     */
    private void write(List<String> actions, String target) {
        StringBuilder sb = new StringBuilder();
        sb.append("Start - Finish:\n");
        for (String line : actions) {
            sb.append(line);
        }
        try (
                FileWriter writer = new FileWriter(target, false)) {
            writer.write(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
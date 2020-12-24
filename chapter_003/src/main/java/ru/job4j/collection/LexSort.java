package ru.job4j.collection;


import java.util.Comparator;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 24.12.2020.
 */
public class LexSort implements Comparator<String> {
    private StringCompare sc = new StringCompare();

    @Override
    public int compare(String left, String right) {
        int result = 0;
        String[] first = left.replace(".", " ").split("\\s");
        String[] second = right.replace(".", " ").split("\\s");
        int delta = first.length >= second.length ? second.length : first.length;
        for (int x = 0; x < delta; x++) {
            String one = first[x];
            String two = second[x];
            if (isDigit(one) && isDigit(two)) {
                result = Integer.compare(Integer.parseInt(one), Integer.parseInt(two));
            } else if (one.equals("") && two.equals("")) {
                continue;
            } else if (one.equals("") || two.equals("")) {
                result = one.equals("") ? -1 : 1;
            } else {
                result = this.sc.compare(one, two);
            }
            if (result != 0) {
                break;
            }
        }
        return result;
    }

    private boolean isDigit(String input) {
        if (input == null || input.length() < 0) {
            return false;
        }
        input = input.trim();
        if ("".equals(input)) {
            return false;
        }
        if (input.startsWith("-")) {
            return input.substring(1).matches("[0-9]*");
        } else {
            return input.matches("[0-9]*");
        }
    }
}

package ru.job4j.unsert;

import java.util.Arrays;

/**
 * InsertSort.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class InsertSort {

    /**
     * Sort elements of word.
     *
     * @param word - word.
     * @return - char[].
     */
    private char[] sort(String word) {
        char[] litters = word.toCharArray();
        char element;
        int inside;
        for (int step = 1; step < litters.length; step++) {
            element = litters[step];
            inside = step;

            while (inside > 0 && litters[inside - 1] > element) {
                litters[inside] = litters[inside - 1];
                inside--;
            }
            litters[inside] = element;
        }
        return litters;
    }

    /**
     * Compare words.
     *
     * @param wordOne - word one.
     * @param wordTwo - word two.
     * @return - result.
     */
    public boolean compareOfWords(String wordOne, String wordTwo) {
        char[] one = sort(wordOne);
        char[] two = sort(wordTwo);
        return Arrays.equals(one, two);
    }
}

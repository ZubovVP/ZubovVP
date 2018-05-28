package ru.job4j.unsert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Add tests.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class InsertSortTest {
    InsertSort test = new InsertSort();

    @Test
    public void takeTwoSimularWordsShouldTrue() {
        String wordOne = "TAKE";
        String wordTwo = "KATE";
        assertTrue(test.compareOfWords(wordOne, wordTwo));
    }

    @Test
    public void takeTwoSameWordsShouldTrue() {
        String wordOne = "SAME";
        String wordTwo = "SAME";
        assertTrue(test.compareOfWords(wordOne, wordTwo));
    }

    @Test
    public void takeTwoDifferentWordsShouldFalse() {
        String wordOne = "TOOK";
        String wordTwo = "LOOK";
        assertFalse(test.compareOfWords(wordOne, wordTwo));
    }

    @Test
    public void takeTwoDifferentWordsShouldFalse2() {
        String wordOne = "TOOK";
        String wordTwo = "OOOK";
        assertFalse(test.compareOfWords(wordOne, wordTwo));
    }

}
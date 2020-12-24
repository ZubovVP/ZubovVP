package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 14.02.2018
 */
public class ListCompare implements Comparator<List<Integer>> {
    /**
     * This method check two collections.
     * @param left - the collection
     * @param right - the collection
     * @return - result
     */
    @Override
    public int compare(List<Integer> left, List<Integer> right) {
        if (left == right) {
            return 0;
        } else if (left == null || right == null) {
            return left == null ? -1 : 1;
        } else if (left.size() != right.size()) {
            return left.size() > right.size() ? 1 : -1;
        } else {
            for (int step = 0; step < left.size(); step++) {
                 if (!left.get(step).equals(right.get(step))) {
                    return left.get(step) > right.get(step) ? 1 : -1;
                }
            }
        }
        return 0;
    }
}
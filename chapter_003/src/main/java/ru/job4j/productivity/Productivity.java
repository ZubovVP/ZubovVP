package ru.job4j.productivity;

import java.util.Collection;

/**
 *This task checks productivity diffrent collections
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 01.02.2018
 */
public class Productivity {
  private long start;
  private  long finish;

    /**
     * This method can add values in the collection
     *
     * @param collection - the collection
     * @param amount - amont
     * @return long - time work
     */
    public long add(Collection<String> collection, int amount) {
        start = System.currentTimeMillis();
        for (int x = 0; x < amount; x++) {
            collection.add(String.valueOf(x));
        }
        finish = System.currentTimeMillis();
        return finish - start;
    }

    /**
     * This method can remove values from the collection
     *
     * @param collection - the collection
     * @param amount - amont
     * @return long - time work
     */
    public long delete(Collection<String> collection, int amount) {
        for (int y = 0; y < amount + 1000; y++) {
            collection.add(String.valueOf(y));
        }
        start = System.currentTimeMillis();
        for (int x = 0; x < amount; x++) {
            collection.remove(String.valueOf(x));
        }
        finish = System.currentTimeMillis();
        return finish - start;
    }
}

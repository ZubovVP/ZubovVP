package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MyHashMap collection.
 *
 * @param <K> - typ key.
 * @param <V> - typ value.
 */
public class MyHashMap<K, V> implements Map<K, V>, Iterable<V> {
    private Entry[] table;
    private int count;
    private int position;

    /**
     * Constructor.
     *
     * @param initialCapacity - volume collection.
     */
    public MyHashMap(int initialCapacity) {
        this.table = new Entry[initialCapacity];
    }

    /**
     *  Constructor.
     *  volume collection = 16.
     */
    public MyHashMap() {
        this.table = new Entry[16];
    }

    /**
     * Add element in the collection
     *
     * @param key   - key.
     * @param value - value.
     * @return  - boolean result.
     */
    @Override
    public boolean insert(K key, V value) {
        boolean result = false;
        checkcapacity();
        position = calculatePosition(key);
        if (this.table[position] == null) {
            this.table[position] = new Entry(key, value);
            this.count++;
            result = true;
        }
        return result;
    }

    /**
     * Give element from collection with key.
     *
     * @param key - key of element.
     * @return - element from the collection.
     */
    @Override
    public V get(K key) {
        position = calculatePosition(key);
        try {
            if (this.table[position] == null) {
                throw new NoSuchElementException();
            }
        } catch (ArrayIndexOutOfBoundsException aIOBE) {
            throw new NoSuchElementException();
        }
        return (V) this.table[position].getValue();
    }

    /**
     * Delete element from the collection.
     *
     * @param key - key of element.
     * @return - result.
     */
    @Override
    public boolean delete(K key) {
        boolean result = false;
        position = calculatePosition(key);
        if (this.table[position] != null) {
            this.table[position] = null;
            result = true;
            this.count--;
            }
        return result;
    }

    /**
     * Check capacity of MyHashSet.
     */
    private void checkcapacity() {
        if ((double) count / this.table.length >= 0.75) {
            this.table = Arrays.copyOf(this.table, this.table.length * 2);
        }
    }

    /**
     * Calculate number position for element.
     *
     * @param key - key.
     * @return - number position.
     */
    private int calculatePosition(K key) {
        if (key == null) {
            throw  new NoSuchElementException();
        }
        return Math.abs(key.hashCode() % this.table.length - 1);
    }

    /**
     * Iterator.
     *
     * @return - iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int expectedModCount = count;
            private int step = 0;


            /**
             * Check next element in the collection.
             *
             * @return - result
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != count) {
                    throw new ConcurrentModificationException();
                }
                for (int x = step; x < table.length; x++) {
                    if (table[x] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            /**
             * Return next element from the collection.
             *
             * @return - element.
             */
            @Override
            public V next() {
                if (expectedModCount != count) {
                    throw new ConcurrentModificationException();
                }
                for (; step < table.length; step++) {
                    if (table[step] != null) {
                        break;
                    }
                }
                if (step == table.length) {
                    throw new NoSuchElementException();
                }
                return (V) table[step++].value;
            }
        };
    }

    /**
     * Container for elements and keys.
     *
     * @param <K> - typ key.
     * @param <V> - typ value.
     */
    private class Entry<K, V> {
        private  K key;
        private V value;

        /**
         * Constructor
         *
         * @param key - key.
         * @param value - value.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Get key.
         *
         * @return - key.
         */
        public K getKey() {
            return key;
        }

        /**
         * Change key.
         *
         * @param key - key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Get value
         *
         * @return - value.
         */
        public V getValue() {
            return value;
        }

        /**
         * Change key.
         *
         * @param value - value.
         */
        public void setValue(V value) {
            this.value = value;
        }
    }
}
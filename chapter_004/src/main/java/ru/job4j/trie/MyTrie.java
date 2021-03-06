package ru.job4j.trie;

import java.util.*;

/**
 * MyTrie
 *
 * @param <T>
 */
public class MyTrie<T> {
    private Node root;

    /**
     * Constructor.
     */
    public MyTrie() {
        this.root = new Node();
    }

    /**
     * Add element in the trie.
     *
     * @param key   - key
     * @param count - count.
     */
    public void add(String key, T count, int index) {
        this.root.add(key, count, index);
    }

    public List<Integer> indexOf(String word) {
        return this.root.getNode(word).getIndexList();

    }


    /**
     * Get Node.
     *
     * @param key - key
     * @return -
     */
    public Set<T> getCounts(String key) {
        Node result = this.root.getNode(key);
        return result == null ? null : result.counts;
    }

    /**
     * Node class.
     */
    private class Node {
        private Set<T> counts;
        private HashMap<Character, Node> childrenList;
        private List<Integer> indexList;

        /**
         * Constructor.
         */
        public Node() {
            this.counts = new HashSet<>();
            this.childrenList = new HashMap<>();
            this.indexList = new ArrayList<>();
        }

        public List<Integer> getIndexList() {
            return indexList;
        }

        /**
         * Add element in the trie.
         *
         * @param key   - key
         * @param count - count.
         */
        private void add(String key, T count, int index) {
            char[] keys = key.toCharArray();
            Node list = root;
            for (char element : keys) {
                if (list.childrenList.containsKey(element)) {
                    list = list.childrenList.get(element);
                } else {
                    list.childrenList.put(element, new Node());
                    list = list.childrenList.get(element);
                }
                if (element == keys[keys.length - 1]) {
                    list.counts.add(count);
                    list.indexList.add(index);

                }
            }
        }

        /**
         * Get Node.
         *
         * @param key - key.
         * @return - Node.
         */
        private Node getNode(String key) {
            Node result = root;
            char[] keys = key.toCharArray();
            for (char element : keys) {
                if (result.childrenList.containsKey(element)) {
                    result = result.childrenList.get(element);
                } else {
                    result = null;
                    break;
                }
            }
            return result;
        }
    }
}


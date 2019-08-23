//package ru.job4j.trie;
//
//
//
//import java.util.*;
//
///**
// * Find word in the file.
// *
// * @author Vitaly Zubov (zubovvp@yandex.ru)
// * @version $Id$
// * @since 0.1
// */
//public class WordIndex {
//    private Trie dictionary;
//    private Map<String, Set<Integer>> table;
//
//    /**
//     * Constructor.
//     */
//    public WordIndex() {
//        this.dictionary = new Trie();
//        this.table = new HashMap<>();
//    }
//
//    /**
//     * Write words in the table. Write
//     *
//     * @param filename - file with the words.
//     */
//    public void loadFile(String filename) {
//        String[] line = filename.split(" ");
//        int step = 0;
//        for (String str : line) {
//            step++;
//            if (this.table.get(str) != null) {
//                this.table.get(str).add(step);
//            } else {
//                this.table.put(str, new HashSet<>());
//                this.table.get(str).add(step);
//            }
//        }
//        for (Map.Entry<String, Set<Integer>> st : this.table.entrySet()) {
//            this.dictionary.put(st.getKey(), st.getValue());
//        }
//    }
//
//    /**
//     * Return list of position.
//     *
//     * @param searchWord - word.
//     * @return - return list of position the searchWord.
//     */
//    public Set<Integer> getIndexes4Word(String searchWord) {
//        return (Set<Integer>) this.dictionary.get(searchWord);
//    }
//}

package ru.job4j.trie;

import java.util.List;
import java.util.Set;

/**
 * Find word in the file.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class WordIndexUseMyTrie {

    private MyTrie dictionary;

    /**
     * Constructor.
     */
    public WordIndexUseMyTrie() {
        this.dictionary = new MyTrie();
    }

    /**
     * Write words in the table.
     *
     * @param filename - file with of the words.
     */
    public void loadFile(String filename) {
        String[] line = filename.split(" ");
        int count = 1;
        int index = 0;
        for (String str : line) {
            this.dictionary.add(str, count++, index);
            index = index + str.length() + 1;
        }
    }

    /**
     * Return list of position.
     *
     * @param searchWord - word.
     * @return - return list of position the searchWord.
     */
    public Set getIndexes4Word(String searchWord) {
        return this.dictionary.getCounts(searchWord);
    }

    /**
     * IndexOf.
     *
     * @param word - word.
     * @return - list of position.
     */
    public List<Integer> indexOf(String word) {
        return this.dictionary.indexOf(word);
    }
}

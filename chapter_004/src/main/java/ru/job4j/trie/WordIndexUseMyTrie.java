package ru.job4j.trie;

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
        int step = 1;
        for (String str : line) {
            this.dictionary.add(str, step++);
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
}

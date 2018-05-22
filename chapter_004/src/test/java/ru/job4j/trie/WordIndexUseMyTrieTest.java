package ru.job4j.trie;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Add tests.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class WordIndexUseMyTrieTest {
    private WordIndexUseMyTrie test = new WordIndexUseMyTrie();
    private Set<Integer> expected = new HashSet<>();


    @Before
    public void start() {
           this.test.loadFile("A B C D F A A B");
    }

    @Test
    public void whenWeFindElementCShould3() throws Exception {
        this.expected.add(3);
        assertThat(this.test.getIndexes4Word("C"), is(this.expected));
    }

    @Test
    public void whenWeFindElementAShould167() throws Exception {
        this.expected.add(1);
        this.expected.add(6);
        this.expected.add(7);
        assertThat(this.test.getIndexes4Word("A"), is(this.expected));
    }

    @Test
    public void whenWeFindElementZShouldNull() throws Exception {
        assertNull(this.test.getIndexes4Word("Z"));
    }
}
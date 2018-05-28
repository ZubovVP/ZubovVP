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
    private String file = "A B C D F A A B";


    @Before
    public void start() {
        this.test.loadFile(file);
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

    @Test
    public void getIndexOfElementBShould1() throws Exception {
        assertThat(this.file.indexOf("A"), is(this.test.indexOf("A").get(0)));
        assertThat(this.file.indexOf("B"), is(this.test.indexOf("B").get(0)));
        assertThat(this.file.indexOf("C"), is(this.test.indexOf("C").get(0)));
        assertThat(this.file.indexOf("D"), is(this.test.indexOf("D").get(0)));

    }

    @Test
    public void testIndexOfWithNewWords() throws Exception {
        WordIndexUseMyTrie testTwo = new WordIndexUseMyTrie();
        String fileTwo = "test text";
        testTwo.loadFile(fileTwo);
        assertThat(fileTwo.indexOf("text"), is(testTwo.indexOf("text").get(0)));
    }
}
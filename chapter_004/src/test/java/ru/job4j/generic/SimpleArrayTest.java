package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleArrayTest {
   private SimpleArray<Integer> simpleArrayIntegerTest = new SimpleArray<Integer>();
   private SimpleArray<String> simpleArrayStringTest = new SimpleArray<String>();
   private String stringTest = "123";
   private int intTest = 5;

   @Before
   public void start() {
       simpleArrayStringTest.add("abc");
       simpleArrayIntegerTest.add(1);
   }

    @Test
    public void add() throws Exception {
        simpleArrayIntegerTest.add(intTest);
        assertThat(simpleArrayIntegerTest.get(1), is(intTest));

        simpleArrayStringTest.add(stringTest);
        assertThat(simpleArrayStringTest.get(1), is(stringTest));
    }

    @Test
    public void set() throws Exception {
        assertThat(simpleArrayIntegerTest.get(0), is(1));
        simpleArrayIntegerTest.set(0, 2);
        assertThat(simpleArrayIntegerTest.get(0), is(2));

        assertThat(simpleArrayStringTest.get(0), is("abc"));
        simpleArrayStringTest.set(0, "AAA");
        assertThat(simpleArrayStringTest.get(0), is("AAA"));
    }

    @Test
    public void delete() throws Exception {
        assertThat(simpleArrayIntegerTest.get(0), is(1));
        simpleArrayIntegerTest.add(intTest);
        simpleArrayIntegerTest.delete(0);
        assertThat(simpleArrayIntegerTest.get(0), is(intTest));

        assertThat(simpleArrayStringTest.get(0), is("abc"));
        simpleArrayStringTest.add(stringTest);
        simpleArrayStringTest.delete(0);
        assertThat(simpleArrayStringTest.get(0), is(stringTest));
    }

    @Test
    public void get() throws Exception {
        assertThat(simpleArrayIntegerTest.get(0), is(1));

        assertThat(simpleArrayStringTest.get(0), is("abc"));
    }

    @Test
    public void hasNext() throws Exception {
        assertTrue(simpleArrayIntegerTest.hasNext());
        simpleArrayIntegerTest.next();
        assertFalse(simpleArrayIntegerTest.hasNext());

        assertTrue(simpleArrayStringTest.hasNext());
        simpleArrayStringTest.next();
        assertFalse(simpleArrayStringTest.hasNext());

    }

    @Test
    public void next() throws Exception {
        simpleArrayIntegerTest.add(intTest);
        assertThat(simpleArrayIntegerTest.next(), is(1));
        assertThat(simpleArrayIntegerTest.next(), is(5));

        simpleArrayStringTest.add(stringTest);
        assertThat(simpleArrayStringTest.next(), is("abc"));
        assertThat(simpleArrayStringTest.next(), is("123"));
    }

}
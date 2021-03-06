package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {

@Test
        public void whenDrawSquare() {
            Square square = new Square();
            assertThat(square.pic(), is(new StringBuilder().append("++++++")
                                                           .append("+    +")
                                                           .append("+    +")
                                                           .append("++++++").toString()));
        }
    }
package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * BishopTest.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
public class BishopTest {
    private Bishop testBishop = new Bishop(new Cell(0,0));


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BishopTest that = (BishopTest) o;

        return testBishop != null ? testBishop.equals(that.testBishop) : that.testBishop == null;
    }

    @Test
    public void wayTest() throws ImposibleMoveException {
        Cell [] result;
        Cell source = new Cell(4,4);
        Cell dest = new Cell(5,5);
            result = testBishop.way(source, dest);

       Cell [] expected = {source, dest};
        assertTrue(expected[0].getX() == result[0].getX());
        assertTrue(expected[0].getY() == result[0].getY());
        assertTrue(expected[1].getX() == result[1].getX());
        assertTrue(expected[1].getY() == result[1].getY());
    }

    @Test
    public void copyTest() throws ImposibleCreateFigure{
        Cell celTest = new Cell(6,6);
        Cell result = testBishop.getPosition(testBishop.copy(celTest));
        Cell expected = testBishop.getPosition(new Bishop(new Cell(6,6)));
        assertEquals(expected, result);
    }

}
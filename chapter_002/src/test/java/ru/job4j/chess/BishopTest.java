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

    @Test
    public void wayTest() throws ImposibleMoveException {
        Cell [] result;
        Cell source = new Cell(4,4);
        Cell dest = new Cell(5,5);
            result = testBishop.way(source, dest);
            // Данным циклом можно увидеть что метод работает правильно, но в связи что у сравниваемых объектов разные
            // хэш коды, поэтому тест не проходит
            /*for(int x = 0; x < result.length; x++){
                System.out.println("индекс массива = " + x);
                System.out.println("X = " + result[x].getX());
                System.out.println("Y = " + result[x].getY());
                System.out.println("Длинна массива " + result.length);
            }*/
            //Можно сравнить два объекта если создать метод getPosition,
        // но как я понял из задания что этого делать не нужно или же данный метод можно реализовать в Bishop
       Cell [] expected = {new Cell(4,4), new Cell(5,5)};
        assertThat(result, is(expected));
    }
    @Test
    public void copyTest() throws ImposibleCreateFigure{
        Board testBoard = new Board();
        Cell celTest = new Cell(6,6);
        testBishop.copy(celTest);
        Bishop expected = new Bishop(new Cell(6,6));
        assertEquals(testBoard, is(expected) );
    }
}
package ru.job4j.chess;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * BoardTest.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
public class BoardTest {
    Board boardTest = new Board();

    @Test
    public void createTest() {
       //проверяем что на доске данная клетка не занята
        boolean result = boardTest.create(new Cell(1, 1));
        assertTrue(result);
        //Добавляем фигуру на доску
        boardTest.addFigure(new Bishop(new Cell(1, 1)), new Cell(1, 1));
        //проверяем можно ли на эту же клетку добавить фигуру на доску
        boolean result2 = boardTest.create(new Cell(1, 1));
        assertFalse(result2);
    }

    @Test
    public void moveTest() throws FigureNotFoundException, ImposibleMoveException, OccupiedWayException, ImposibleCreateFigure {
        //добавили фигуру на доску
        boardTest.addFigure(new Bishop(new Cell(5, 5)), new Cell(5, 5));
        //проверяем фигуру на доскке
        Figure[][] figures = boardTest.getBoard();
        boolean excpected = figures[5][5] != null;
        assertTrue(excpected);
        //делаем ход
        boolean expected1 = boardTest.move(new Cell(5, 5), new Cell(1, 1));
        //проверяем что фигра переместилась на доске
        Figure[][] figures1 = boardTest.getBoard();
        boolean excpected1 = figures1[1][1] != null;
        assertTrue(expected1);
        // проверяем что фигура ушла оо старой клетки
        boolean expected3 = figures[5][5] != null;
        assertFalse(expected3);
    }

}
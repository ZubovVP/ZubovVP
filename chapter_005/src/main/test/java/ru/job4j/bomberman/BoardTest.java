package ru.job4j.bomberman;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by ZubovVP on 24.07.2018
 * zubovvp@yadndex.ru
 */
public class BoardTest {
    private Board boardTest;

    @Before
    public void start() {
        this.boardTest = new Board(5, 5);
    }

    @Test
    public void fillBoardShouldNotNull() {
        assertNull(boardTest.getLock(1, 1));
        assertNull(boardTest.getLock(4, 4));
        boardTest.fill();
        assertNotNull(boardTest.getLock(1, 1));
        assertNotNull(boardTest.getLock(4, 4));
    }

    @Test
    public void testMoveMainHero() {
        boardTest.fill();
        MainHero hero = new MainHero(new Cell(0, 0), boardTest);
        hero.start();
        try {
            Thread.sleep(100);
            hero.interrupt();
            hero.join();
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @Test(expected = IOException.class)
//    public void WriteWrongSourceShouldException() {
//        this.boardTest.fill();
//        MainHero hero = new MainHero(new Cell(10, 10), boardTest);
//        Thread td = new Thread(hero);
//        td.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
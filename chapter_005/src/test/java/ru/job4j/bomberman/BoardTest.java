package ru.job4j.bomberman;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

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
    public void checkPositionTest() {
        assertTrue(boardTest.checkPosition(new Cell(0, 0)));
        assertTrue(boardTest.checkPosition(new Cell(2, 3)));
        assertFalse(boardTest.checkPosition(new Cell(0, 7)));
        assertFalse(boardTest.checkPosition(new Cell(10, 50)));
    }

    @Test
    public void getLengthTestShouldFive() {
        assertThat(this.boardTest.getLength(), is(5));
        assertThat(this.boardTest.getLength(2), is(5));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getLockTest() {
        Lock lockTest;
        boardTest.fill();
        lockTest = this.boardTest.getLock(1, 1);
        assertNotNull(lockTest);
        this.boardTest.getLock(30, 3);
    }
    ExecutorService ex = Executors.newFixedThreadPool(1);

}
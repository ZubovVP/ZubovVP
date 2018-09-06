package ru.job4j.bomberman;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ZubovVP on 14.07.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class Board {
    @GuardedBy("this")
    private final Lock[][] locks;

    /**
     * Constructor.
     *
     * @param x - coordinate x.
     * @param y - coordinate y.
     */
    public Board(int x, int y) {
        this.locks = new ReentrantLock[x][y];
    }

    /**
     * Get lock in the board.
     *
     * @param x - coordinate x.
     * @param y - coordinate y.
     * @return - the lock.
     */
    public Lock getLock(int x, int y) {
        return this.locks[x][y];
    }


    /**
     * Get length the board.
     *
     * @return amount.
     */
    public int getLength() {
        return this.locks.length;
    }

    /**
     * Get length the board.
     *
     * @param x - coordinate x.
     * @return - amount.
     */
    public int getLength(int x) {
        return this.locks[x].length;
    }

    /**
     * Create locks in the board.
     */
    public void fill() {
        for (Lock[] row : this.locks) {
            Arrays.fill(row, new ReentrantLock());
        }
    }

    /**
     * Check monitor of the locks.
     *
     * @param lock - lock.
     * @return - result.
     */
    private boolean takeLocks(Lock lock) {
        boolean secondLockTaken = false;
        try {
            secondLockTaken = lock.tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return secondLockTaken;
    }

    /**
     * Move the hero on the board.
     *
     * @param source - start place.
     * @param dist   - finish place.
     * @return - result.
     */
    public boolean move(Cell source, Cell dist) {
        boolean result = takeLocks(getLock(dist.getX(), dist.getY()));
        if (result) {
            getLock(source.getX(), source.getY()).unlock();
        }
        return result;
    }

    /**
     * Check position.
     *
     * @param source - source on the board.
     * @return - true - right, false - wrong.
     */
    public boolean checkPosition(Cell source) {
        return (source.getX() <= getLength() && source.getY() <= getLength(source.getX()) && source.getX() >= 0 && source.getY() >= 0);
    }
}

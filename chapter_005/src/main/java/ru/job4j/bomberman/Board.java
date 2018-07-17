package ru.job4j.bomberman;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ZubovVP on 14.07.2018
 * zubovvp@yadndex.ru
 */
public class Board {
    private Lock[][] locks;

    public Board(int x, int y) {
        this.locks = new ReentrantLock[x][y];
    }

    public Lock getLock(int x, int y) {
        return this.locks[x][y];
    }


    public int getLength() {
        return this.locks.length;
    }

    public int getLength(int x){
        return this.locks[x].length;
    }

    public void fill() {
        for(Lock[] row : this.locks) {
            Arrays.fill(row, new ReentrantLock());
        }
    }

    private boolean takeLocks(Lock lock1, Lock lock2)  {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;
        //Несмотря на то что я заблокировал данный лок за героем в момент создания на достке у меня опять появляется true
            firstLockTaken = lock1.tryLock();
        try {
            secondLockTaken = lock2.tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return !firstLockTaken && secondLockTaken;
    }

    public boolean move(Cell source, Cell dist)  {
        boolean result = takeLocks(getLock(source.getX(), source.getY()),getLock(dist.getX(), dist.getY()));
    //    System.out.println("RESULT = " + result);
        return  result;
    }
}

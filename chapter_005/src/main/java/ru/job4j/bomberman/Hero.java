package ru.job4j.bomberman;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ZubovVP on 14.07.2018
 * zubovvp@yadndex.ru
 */
public abstract class Hero extends Thread {
    final Cell source;
    final Board board;
    final Random rd = ThreadLocalRandom.current();


    /**
     * Constructor.
     *
     * @param source - start place.
     * @param board - board.
     */
    public Hero(Cell source, Board board) {
        this.source = source;
        this.board = board;
    }
}

package ru.job4j.bomberman;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZubovVP on 16.08.2018
 * zubovvp@yadndex.ru
 */
public class Creep extends Hero {
    private boolean running = true;
    private boolean takenLock;
    private boolean result;
    private Cell dist;

    /**
     * Constructor.
     *
     * @param source - start place.
     * @param board  - board.
     */
    public Creep(Cell source, Board board) {
        super(source, board);
    }

    @Override
    public void run() {
        result = this.board.checkPosition(source);
        if (result) {
            while (!takenLock) {
                try {
                    takenLock = this.board.getLock(source.getX(), source.getY()).tryLock(100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            action();
        } else {
            try {
                throw new IOException("Wrong source");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Print.
     */
    private void print() {
        System.out.println(Thread.currentThread().getName() + " - CREEP");
        System.out.println("X = " + this.source.getX());
        System.out.println("Y = " + this.source.getY());
        System.out.println("------------------------");
    }

    public void shutdown() {
        System.out.println(Thread.currentThread().getName() + "FINISH!");
        running = false;
        Thread.currentThread().interrupt();
    }

    private void action() {
        while (running) {
            result = false;
            int count = rd.nextInt(4);

            if (count == 0 && this.source.getY() > 0) {
                dist = new Cell(this.source.getX(), this.source.getY() - 1);

            } else if (count == 1 && this.source.getX() + 1 < this.board.getLength()) {
                dist = new Cell(this.source.getX() + 1, this.source.getY());

            } else if (count == 2 && this.source.getY() + 1 < this.board.getLength(this.source.getX())) {
                dist = new Cell(this.source.getX(), this.source.getY() + 1);

            } else if (count == 3 && this.source.getX() > 0) {
                dist = new Cell(this.source.getX() - 1, this.source.getY());
            }
            result = this.board.move(this.source, dist);
            if (result) {
                this.source.setX(dist.getX());
                this.source.setY(dist.getY());
              //  print();
                Thread.yield();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

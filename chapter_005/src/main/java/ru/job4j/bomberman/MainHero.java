package ru.job4j.bomberman;

import java.io.IOException;

/**
 * Created by ZubovVP on 15.07.2018
 * zubovvp@yadndex.ru
 */
public class MainHero extends Hero {

    public MainHero(Cell source, Board board) {
        super(source, board);
    }

    @Override
    public void run() {
        int count;
        if (source.getX() <= this.board.getLength() && source.getY() <= this.board.getLength(source.getX())) {
            boolean result = false;
            while (!result) {
                //При создании героя на поле я заблокировал за ним лок(выдаёт true)
                result = this.board.getLock(source.getX(), source.getY()).tryLock();
                //System.out.println(result);
            }
            while (!Thread.currentThread().isInterrupted()) {
                count = rd.nextInt(4);
                Cell dist = null;

                if (count == 0 && this.source.getY() > 0) {
                    dist = new Cell(this.source.getX(), this.source.getY() - 1);
                    print();

                } else if (count == 1 && this.source.getX() + 1 < this.board.getLength()) {
                    dist = new Cell(this.source.getX() + 1, this.source.getY());
                    print();

                } else if (count == 2 && this.source.getY() + 1 < this.board.getLength(this.source.getX())) {
                    dist = new Cell(this.source.getX(), this.source.getY() + 1);
                    print();

                } else if (count == 3 && this.source.getX() > 0) {
                    dist = new Cell(this.source.getX() - 1, this.source.getY());
                    print();
                }

                if (dist != null) {
                    result = this.board.move(this.source, dist);
                    if (result) {
                        this.board.getLock(source.getX(), source.getY()).unlock();
                        this.source.setX(dist.getX());
                        this.source.setY(dist.getY());
                    }
                }
            }
        } else {
            try {
                throw new IOException("Wrong source");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void print() {
        System.out.println("X = " + this.source.getX());
        System.out.println("Y = " + this.source.getY());
        System.out.println("------------------------");
    }
}

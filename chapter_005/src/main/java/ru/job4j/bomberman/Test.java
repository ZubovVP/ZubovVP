package ru.job4j.bomberman;

/**
 * Created by ZubovVP on 15.07.2018
 * zubovvp@yadndex.ru
 */
public class Test {
    public static void main(String[] args)  {
        Board board = new Board(10, 10);
        board.fill();
        MainHero hero = new MainHero(new Cell(0,0), board);
        hero.start();

        try {
            Thread.sleep(1000);
            hero.interrupt();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

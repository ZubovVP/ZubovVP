package ru.job4j.bomberman;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ZubovVP on 17.08.2018
 * zubovvp@yadndex.ru
 */
public class StartGame {
    private Scanner sc = new Scanner(System.in);
    private Board board;

    private void start() throws IOException, InterruptedException {
        System.out.println("Задайде площадь доски :");
        board = new Board(sc.nextInt(), sc.nextInt());
        this.board.fill();
        System.out.println("Введите колличество крипов");
        int creeps = sc.nextInt();
        for (int x = 0; x < creeps; x++) {
            new Creep(new Cell(0, 0), this.board).start();
        }
        System.out.println("Create main hero, write coordinate");
        System.out.print("X : ");
        MainHero mh = new MainHero(new Cell(sc.nextInt(), sc.nextInt()), this.board);
        mh.start();
        mh.join();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new StartGame().start();
    }
}

package ru.job4j.bomberman;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ZubovVP on 15.07.2018
 * zubovvp@yadndex.ru
 */
public class MainHero extends Hero {
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);
    private boolean takenLock;


    /**
     * Constructor.
     *
     * @param source - start place.
     * @param board  - board.
     */
    public MainHero(Cell source, Board board) {
        super(source, board);
    }

    @Override
    public void run() {

        boolean result = this.board.checkPosition(source);
        if (result) {
            while (!takenLock) {
                    takenLock = this.board.getLock(source.getX(), source.getY()).tryLock();

            }
            while (running) {
                System.out.println("pls write dist");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Cell dist = new Cell(x, y);

                if ((dist.getX() + 1 == this.source.getX() || dist.getX() - 1 == this.source.getX() || dist.getX() == this.source.getX()) && (dist.getY() + 1 == this.source.getY() || dist.getY() - 1 == this.source.getY() || dist.getY() == this.source.getY())) {
                        result = this.board.move(this.source, dist);
                    if (result) {
                        this.source.setX(dist.getX());
                        this.source.setY(dist.getY());
                        print();

                    }
                    System.out.println("Exit? Yes/No");
                    String answer = scanner.next();
                    if (answer.equals("Yes")) {
                        shutdown();
                    }
                } else {
                    System.out.println("Wrong dist, please write correct dist");
                    print();
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

    /**
     * Print.
     */
    private void print() {
        System.out.println(Thread.currentThread().getName() + "HERO");
        System.out.println("X = " + this.source.getX());
        System.out.println("Y = " + this.source.getY());
        System.out.println("------------------------");
    }

    /**
     * Finish game.
     */
    public void shutdown() {
        System.out.println("FINISH!");
        running = false;
        Thread.currentThread().interrupt();
    }
}

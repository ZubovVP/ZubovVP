package ru.job4j.game;

import ru.job4j.game.player.AbstractPlayer;
import ru.job4j.game.check.CheckWin;
import ru.job4j.game.view.ShowField;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.02.2020.
 */
public class TickTackToe {
    private int round;
    private AbstractPlayer player1;
    private AbstractPlayer player2;
    private CheckWin checkWin;
    private Field field;
    private ShowField showField;

    /**
     * Constructor.
     *
     * @param round     - quantity rounds for game.
     * @param sizeField - size of field.
     * @param player1   - player 1.
     * @param player2   - player 2.
     * @param showField - show field.
     * @param checkWin  - checker.
     */
    public TickTackToe(int round, int sizeField, AbstractPlayer player1, AbstractPlayer player2, ShowField showField, CheckWin checkWin) {
        this.round = round;
        this.player1 = player1;
        this.player2 = player2;
        this.field = new Field(sizeField);
        this.showField = showField;
        this.checkWin = checkWin;
    }

    /**
     * Constructor.
     *
     * @param round     - quantity rounds for game.
     * @param field     - field.
     * @param player1   - player 1.
     * @param player2   - player 2.
     * @param showField - show field.
     * @param checkWin  - checker.
     */
    public TickTackToe(int round, Field field, AbstractPlayer player1, AbstractPlayer player2, ShowField showField, CheckWin checkWin) {
        this.round = round;
        this.player1 = player1;
        this.player2 = player2;
        this.field = field;
        this.showField = showField;
        this.checkWin = checkWin;
    }

    /**
     * Start game.
     */
    public void game() {
        boolean flag = true;
        while (this.round > 0) {
            if (flag) {
                turn(this.player1);
                flag = false;
            } else {
                turn(this.player2);
                flag = true;
            }
        }
        if (this.player1.getWin() > this.player2.getWin()) {
            System.out.println(String.format("%s - Win!", this.player1.getName()));
        }
        if (this.player1.getWin() < this.player2.getWin()) {
            System.out.println(String.format("%s - Win!", this.player2.getName()));
        } else {
            System.out.println("Draw");
        }
    }

    /**
     * Turn any player.
     *
     * @param player - player.
     */
    private void turn(AbstractPlayer player) {
        boolean result = false;
        System.out.println(this.showField.show(this.field));
        System.out.println(String.format("%s turn :", player.getName()));
        System.out.println("Please, write your coordinates (X,Y) :");
        if (!player.move(this.field)) {
            this.round--;
            this.field.clearAll();
            System.out.println("Draw");
            result = true;
        }
        if (!result && checkWin.checkWin(player1.getOwnSymbol())) {
            player.setWin(player.getWin() + 1);
            System.out.println(String.format("%s win!", player.getName()));
            System.out.println(String.format("%s - %s : %s - %s:", this.player1.getName(), this.player1.getWin(), player2.getName(), this.player2.getWin()));
            this.round--;
            this.field.clearAll();
        }
    }
}

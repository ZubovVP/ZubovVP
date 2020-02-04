package ru.job4j.game.player;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.01.2020.
 */
public abstract class AbstractPlayer {
    private final String name;
    private int win;
    private final char ownSymbol;

    public AbstractPlayer(String name, char ownSymbol) {
        this.name = name;
        this.ownSymbol = ownSymbol;
    }

    public abstract boolean move(Field field);

    public char getOwnSymbol() {
        return this.ownSymbol;
    }

    public String getName() {
        return this.name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }
}

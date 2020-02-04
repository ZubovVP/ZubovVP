package ru.job4j.game.player;

import ru.job4j.game.Field;
import ru.job4j.game.check.CheckCoordinate;
import ru.job4j.game.input.Input;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.01.2020.
 */
public class Player extends AbstractPlayer implements AutoCloseable {
    private CheckCoordinate checker;
    private Input input = new Input();

    public Player(String name, char ownSymbol, CheckCoordinate checker) {
        super(name, ownSymbol);
        this.checker = checker;
    }

    @Override
    public boolean move(Field field) {
        boolean result = false;
        while (!result) {
            String turn = this.input.read();
            int x = Integer.parseInt(turn.split(",")[0]);
            int y = Integer.parseInt(turn.split(",")[1]);
            if (this.checker.check(field, y, x)) {
                result = field.add(y, x, this.getOwnSymbol());
            }
        }
        return true;
    }

    @Override
    public void close() {
        this.input.close();
    }
}

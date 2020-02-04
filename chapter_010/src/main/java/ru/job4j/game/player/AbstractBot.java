package ru.job4j.game.player;

import ru.job4j.game.Field;

import java.util.Random;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.01.2020.
 */
public abstract class AbstractBot extends AbstractPlayer implements Action {
    private String check = String.format("[^%s\\W]", this.getOwnSymbol());

    public AbstractBot(String name, char ownSymbol) {
        super(name, ownSymbol);
    }

    /**
     * Insert symbol on the field.
     *
     * @param field - field.
     * @return - result.
     */
    @Override
    public boolean move(Field field) {
        boolean result = defense(field);
        if (!result) {
            result = attack(field);
        }
        return result;
    }

    /**
     * Insert symbol on the field on random place.
     *
     * @param field - field.
     * @return - result.
     */
    protected boolean randomMove(Field field) {
        boolean check = false;
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (check) {
                while (!result) {
                    Random random = new Random();
                    result = field.add(random.nextInt(field.getSize()), random.nextInt(field.getSize()), this.getOwnSymbol());
                }
                break;
            }
            for (int y = 0; y < field.getField().length; y++) {
                if (field.getSymbol(x, y) == ' ') {
                    check = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Check is an enemy symbol.
     *
     * @param symbol - checkable symbol.
     * @return - result.
     */
    protected boolean isEnemySymbol(char symbol) {
        return String.valueOf(symbol).matches(this.check);
    }
}

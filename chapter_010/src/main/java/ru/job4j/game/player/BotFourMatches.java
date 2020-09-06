package ru.job4j.game.player;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.01.2020.
 */
public class BotFourMatches extends AbstractBot {

    public BotFourMatches(String name, char ownSymbol) {
        super(name, ownSymbol);
    }

    /**
     * Find own symbol and check is empty follow place if not that use random move.
     *
     * @param field - field.
     * @return - result.
     */
    private boolean commonMove(Field field) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (result) {
                break;
            }
            for (int y = 0; y < field.getField().length; y++) {
                if (isEnemySymbol(field.getSymbol(x, y))) {
                    if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x + 1, y + 1)) && field.getSymbol(x + 2, y + 2) == ' ' && field.getSymbol(x + 3, y + 3) == ' ') {
                        result = field.add(x + 2, y + 2, getOwnSymbol());
                        break;
                    } else if (field.getField().length > x + 3 && isEnemySymbol(field.getSymbol(x + 1, y)) && field.getSymbol(x + 2, y) == ' ' && field.getSymbol(x + 3, y) == ' ') {
                        result = field.add(x + 2, y, getOwnSymbol());
                        break;
                    } else if (field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x, y + 1)) && field.getSymbol(x, y + 2) == ' ' && field.getSymbol(x, y + 3) == ' ') {
                        result = field.add(x, y + 2, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && y - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y - 1)) && field.getSymbol(x - 2, y - 2) == ' ' && field.getSymbol(x - 3, y - 3) == ' ') {
                        result = field.add(x - 2, y - 2, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y)) && field.getSymbol(x - 2, y) == ' ' && field.getSymbol(x - 3, y) == ' ') {
                        result = field.add(x - 2, y, getOwnSymbol());
                        break;
                    } else if (y - 3 >= 0 && isEnemySymbol(field.getSymbol(x, y - 1)) && field.getSymbol(x, y - 2) == ' ' && field.getSymbol(x, y - 3) == ' ') {
                        result = field.add(x, y - 2, getOwnSymbol());
                        break;
                    }
                } else if (field.getSymbol(x, y) == ' ') {
                    if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x + 1, y + 1)) && isEnemySymbol(field.getSymbol(x + 2, y + 2))) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (field.getField().length > x + 3 && isEnemySymbol(field.getSymbol(x + 1, y)) && isEnemySymbol(field.getSymbol(x + 2, y))) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x, y + 1)) && isEnemySymbol(field.getSymbol(x, y + 2))) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && y - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y - 1)) && isEnemySymbol(field.getSymbol(x - 2, y - 2))) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y)) && isEnemySymbol(field.getSymbol(x - 2, y))) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (y - 3 >= 0 && isEnemySymbol(field.getSymbol(x, y - 1)) && isEnemySymbol(field.getSymbol(x, y - 2)) && isEnemySymbol(field.getSymbol(x, y - 3))) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    }
                }
            }
        }
        if (!result) {
            for (int x = 0; x < field.getField().length; x++) {
                if (result) {
                    break;
                }
                for (int y = 0; y < field.getField().length; y++) {
                    if (field.getSymbol(x, y) == getOwnSymbol()) {
                        if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && field.getSymbol(x + 1, y + 1) == ' ' && field.getSymbol(x + 2, y + 2) == ' ' && field.getSymbol(x + 3, y + 3) == ' ') {
                            result = field.add(x + 1, y + 1, getOwnSymbol());
                            break;
                        } else if (field.getField().length > x + 3 && field.getSymbol(x + 1, y) == ' ' && field.getSymbol(x + 2, y) == ' ' && field.getSymbol(x + 3, y) == ' ') {
                            result = field.add(x + 1, y, getOwnSymbol());
                            break;
                        } else if (field.getField()[x].length > y + 3 && field.getSymbol(x, y + 1) == ' ' && field.getSymbol(x, y + 2) == ' ' && field.getSymbol(x, y + 3) == ' ') {
                            result = field.add(x, y + 1, getOwnSymbol());
                            break;
                        } else if (x - 3 >= 0 && y - 3 >= 0 && field.getSymbol(x - 1, y - 1) == ' ' && field.getSymbol(x - 2, y - 2) == ' ' && field.getSymbol(x - 3, y - 3) == ' ') {
                            result = field.add(x - 1, y - 1, getOwnSymbol());
                            break;
                        } else if (x - 3 >= 0 && field.getSymbol(x - 1, y) == ' ' && field.getSymbol(x - 2, y) == ' ' && field.getSymbol(x - 3, y) == ' ') {
                            result = field.add(x - 1, y, getOwnSymbol());
                            break;
                        } else if (y - 3 >= 0 && field.getSymbol(x, y - 1) == ' ' && field.getSymbol(x, y - 2) == ' ' && field.getSymbol(x, y - 3) == ' ') {
                            result = field.add(x, y - 1, getOwnSymbol());
                            break;
                        }
                    }
                }
            }
        }
        if (!result) {
            for (int x = 0; x < field.getField().length; x++) {
                if (!result) {
                    for (int y = 0; y < field.getField()[x].length; y++) {
                        if (field.getSymbol(x, y) == ' ') {
                            while (!result) {
                                result = randomMove(field);
                            }
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Check the follow enemy symbol insert own symbol against the other player.
     *
     * @param field - field.
     * @return - result.
     */
    @Override
    public boolean defense(Field field) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (result) {
                break;
            }
            for (int y = 0; y < field.getField()[x].length; y++) {
                if ((isEnemySymbol(field.getSymbol(x, y)))) {
                    if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x + 1, y + 1)) && isEnemySymbol(field.getSymbol(x + 2, y + 2)) && field.getSymbol(x + 3, y + 3) == ' ') {
                        result = field.add(x + 3, y + 3, this.getOwnSymbol());
                        break;
                    } else if (field.getField().length > x + 3 && isEnemySymbol(field.getSymbol(x + 1, y)) && isEnemySymbol(field.getSymbol(x + 2, y)) && field.getSymbol(x + 3, y) == ' ') {
                        result = field.add(x + 3, y, this.getOwnSymbol());
                        break;
                    } else if (field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x, y + 1)) && isEnemySymbol(field.getSymbol(x, y + 2)) && field.getSymbol(x, y + 3) == ' ') {
                        result = field.add(x, y + 3, this.getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && y - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y - 1)) && isEnemySymbol(field.getSymbol(x - 2, y - 2)) && field.getSymbol(x - 3, y - 3) == ' ') {
                        result = field.add(x - 3, y - 3, this.getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y)) && isEnemySymbol(field.getSymbol(x - 2, y)) && field.getSymbol(x - 3, y) == ' ') {
                        result = field.add(x - 3, y, this.getOwnSymbol());
                        break;
                    } else if (y - 3 >= 0 && isEnemySymbol(field.getSymbol(x, y - 1)) && isEnemySymbol(field.getSymbol(x, y - 2)) && field.getSymbol(x, y - 3) == ' ') {
                        result = field.add(x, y - 3, this.getOwnSymbol());
                        break;
                    }
                } else if (field.getSymbol(x, y) == ' ') {
                    if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x + 1, y + 1)) && isEnemySymbol(field.getSymbol(x + 2, y + 2)) && isEnemySymbol(field.getSymbol(x + 3, y + 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (field.getField().length > x + 3 && isEnemySymbol(field.getSymbol(x + 1, y)) && isEnemySymbol(field.getSymbol(x + 2, y)) && isEnemySymbol(field.getSymbol(x + 3, y))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x, y + 1)) && isEnemySymbol(field.getSymbol(x, y + 2)) && isEnemySymbol(field.getSymbol(x, y + 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && y - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y - 1)) && isEnemySymbol(field.getSymbol(x - 2, y - 2)) && isEnemySymbol(field.getSymbol(x - 3, y - 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y)) && isEnemySymbol(field.getSymbol(x - 2, y)) && isEnemySymbol(field.getSymbol(x - 3, y))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (y - 3 >= 0 && isEnemySymbol(field.getSymbol(x, y - 1)) && isEnemySymbol(field.getSymbol(x, y - 2)) && isEnemySymbol(field.getSymbol(x, y - 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    }
                    //добавил тупо для более актуальной логики
                } else if (field.getSymbol(x, y) == ' ') {
                    if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x + 1, y + 1)) && field.getSymbol(x + 2, y + 2) == ' ' && isEnemySymbol(field.getSymbol(x + 3, y + 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (field.getField().length > x + 3 && isEnemySymbol(field.getSymbol(x + 1, y)) && field.getSymbol(x + 2, y) == ' ' && isEnemySymbol(field.getSymbol(x + 3, y))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (field.getField()[x].length > y + 3 && isEnemySymbol(field.getSymbol(x, y + 1)) && field.getSymbol(x, y + 2) == ' ' && isEnemySymbol(field.getSymbol(x, y + 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && y - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y - 1)) && field.getSymbol(x - 2, y - 2) == ' ' && isEnemySymbol(field.getSymbol(x - 3, y - 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && isEnemySymbol(field.getSymbol(x - 1, y)) && field.getSymbol(x - 2, y) == ' ' && isEnemySymbol(field.getSymbol(x - 3, y))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    } else if (y - 3 >= 0 && isEnemySymbol(field.getSymbol(x, y - 1)) && field.getSymbol(x, y - 2) == ' ' && isEnemySymbol(field.getSymbol(x, y - 3))) {
                        result = field.add(x, y, this.getOwnSymbol());
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Check the final move if not that insert own symbol near own symbol if not that random move..
     *
     * @param field - field.
     * @return - result.
     */
    @Override
    public boolean attack(Field field) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (result) {
                break;
            }
            for (int y = 0; y < field.getField().length; y++) {
                if (field.getSymbol(x, y) == getOwnSymbol()) {
                    if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && field.getSymbol(x + 1, y + 1) == getOwnSymbol() && field.getSymbol(x + 2, y + 2) == getOwnSymbol() && field.getSymbol(x + 3, y + 3) == ' ') {
                        result = field.add(x + 3, y + 3, getOwnSymbol());
                        break;
                    } else if (field.getField().length > x + 3 && field.getSymbol(x + 1, y) == getOwnSymbol() && field.getSymbol(x + 2, y) == getOwnSymbol() && field.getSymbol(x + 3, y) == ' ') {
                        result = field.add(x + 3, y, getOwnSymbol());
                        break;
                    } else if (field.getField()[x].length > y + 3 && field.getSymbol(x, y + 1) == getOwnSymbol() && field.getSymbol(x, y + 2) == getOwnSymbol() && field.getSymbol(x, y + 3) == ' ') {
                        result = field.add(x, y + 3, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && y - 3 >= 0 && field.getSymbol(x - 1, y - 1) == getOwnSymbol() && field.getSymbol(x - 2, y - 2) == getOwnSymbol() && field.getSymbol(x - 3, y - 3) == ' ') {
                        result = field.add(x - 3, y - 3, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && field.getSymbol(x - 1, y) == getOwnSymbol() && field.getSymbol(x - 2, y) == getOwnSymbol() && field.getSymbol(x - 3, y) == ' ') {
                        result = field.add(x - 3, y, getOwnSymbol());
                        break;
                    } else if (y - 3 >= 0 && field.getSymbol(x, y - 1) == getOwnSymbol() && field.getSymbol(x, y - 2) == getOwnSymbol() && field.getSymbol(x, y - 3) == ' ') {
                        result = field.add(x, y - 3, getOwnSymbol());
                        break;
                    }
                } else if (field.getSymbol(x, y) == ' ') {
                    if (field.getField().length > x + 3 && field.getField()[x].length > y + 3 && field.getSymbol(x + 1, y + 1) == getOwnSymbol() && field.getSymbol(x + 2, y + 2) == getOwnSymbol() && field.getSymbol(x + 3, y + 3) == getOwnSymbol()) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (field.getField().length > x + 3 && field.getSymbol(x + 1, y) == getOwnSymbol() && field.getSymbol(x + 2, y) == getOwnSymbol() && field.getSymbol(x + 3, y) == getOwnSymbol()) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (field.getField()[x].length > y + 3 && field.getSymbol(x, y + 1) == getOwnSymbol() && field.getSymbol(x, y + 2) == getOwnSymbol() && field.getSymbol(x, y + 3) == getOwnSymbol()) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && y - 3 >= 0 && field.getSymbol(x - 1, y - 1) == getOwnSymbol() && field.getSymbol(x - 2, y - 2) == getOwnSymbol() && field.getSymbol(x - 3, y - 3) == getOwnSymbol()) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (x - 3 >= 0 && field.getSymbol(x - 1, y) == getOwnSymbol() && field.getSymbol(x - 2, y) == getOwnSymbol() && field.getSymbol(x - 3, y) == getOwnSymbol()) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    } else if (y - 3 >= 0 && field.getSymbol(x, y - 1) == getOwnSymbol() && field.getSymbol(x, y - 2) == getOwnSymbol() && field.getSymbol(x, y - 3) == getOwnSymbol()) {
                        result = field.add(x, y, getOwnSymbol());
                        break;
                    }
                }
            }
        }
        if (!result) {
            result = commonMove(field);
        }
        return result;
    }
}
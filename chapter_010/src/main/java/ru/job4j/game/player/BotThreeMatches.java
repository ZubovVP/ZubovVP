package ru.job4j.game.player;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.01.2020.
 */
public class BotThreeMatches extends AbstractBot {

    public BotThreeMatches(String name, char ownSymbol) {
        super(name, ownSymbol);
    }

    @Override
    public boolean defense(Field field) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (!result) {
                for (int y = 0; y < field.getField()[x].length; y++) {
                    if (isEnemySymbol(field.getSymbol(x, y))) {
                        if (x + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x + 1, y)) && field.getSymbol(x + 2, y) == ' ') {
                            result = field.add(x + 2, y, getOwnSymbol());
                            break;
                        } else if (x + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x + 2, y)) && field.getSymbol(x + 1, y) == ' ') {
                            result = field.add(x + 1, y, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x, y + 1)) && field.getSymbol(x, y + 2) == ' ') {
                            result = field.add(x, y + 2, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x, y + 2)) && field.getSymbol(x, y + 1) == ' ') {
                            result = field.add(x, y + 1, getOwnSymbol());
                            break;
                        }
                    } else if (field.getSymbol(x, y) == ' ') {
                        if (x + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x + 1, y)) && isEnemySymbol(field.getSymbol(x + 2, y))) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        } else if (x + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x + 2, y)) && isEnemySymbol(field.getSymbol(x + 1, y))) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x, y + 1)) && isEnemySymbol(field.getSymbol(x, y + 2))) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && isEnemySymbol(field.getSymbol(x, y + 2)) && isEnemySymbol(field.getSymbol(x, y + 1))) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        }
                    }
                }
            }
            if (!result) {
                if (isEnemySymbol(field.getSymbol(0, 0)) && isEnemySymbol(field.getSymbol(1, 1)) && field.getSymbol(2, 2) == ' ') {
                    result = field.add(2, 2, getOwnSymbol());
                } else if (isEnemySymbol(field.getSymbol(0, 0)) && isEnemySymbol(field.getSymbol(2, 2)) && field.getSymbol(1, 1) == ' ') {
                    result = field.add(1, 1, getOwnSymbol());
                } else if (isEnemySymbol(field.getSymbol(2, 2)) && isEnemySymbol(field.getSymbol(1, 1)) && field.getSymbol(0, 0) == ' ') {
                    result = field.add(0, 0, getOwnSymbol());
                } else if (isEnemySymbol(field.getSymbol(1, 1)) && isEnemySymbol(field.getSymbol(2, 2)) && field.getSymbol(0, 0) == ' ') {
                    result = field.add(0, 0, getOwnSymbol());
                } else if (isEnemySymbol(field.getSymbol(2, 0)) && isEnemySymbol(field.getSymbol(1, 1)) && field.getSymbol(0, 2) == ' ') {
                    result = field.add(0, 2, getOwnSymbol());
                } else if (isEnemySymbol(field.getSymbol(0, 2)) && isEnemySymbol(field.getSymbol(1, 1)) && field.getSymbol(2, 0) == ' ') {
                    result = field.add(2, 0, getOwnSymbol());
                } else if (isEnemySymbol(field.getSymbol(0, 2)) && isEnemySymbol(field.getSymbol(2, 0)) && field.getSymbol(1, 1) == ' ') {
                    result = field.add(1, 1, getOwnSymbol());
                }
            }
        }
        return result;
    }

    @Override
    public boolean attack(Field field) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (!result) {
                for (int y = 0; y < field.getField()[x].length; y++) {
                    if (field.getSymbol(x, y) == getOwnSymbol()) {
                        if (x + 2 < field.getField().length && field.getSymbol(x + 1, y) == getOwnSymbol() && field.getSymbol(x + 2, y) == ' ') {
                            result = field.add(x + 2, y, getOwnSymbol());
                            break;
                        } else if (x + 2 < field.getField().length && field.getSymbol(x + 2, y) == getOwnSymbol() && field.getSymbol(x + 1, y) == ' ') {
                            result = field.add(x + 1, y, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && field.getSymbol(x, y + 1) == getOwnSymbol() && field.getSymbol(x, y + 2) == ' ') {
                            result = field.add(x, y + 2, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && field.getSymbol(x, y + 2) == getOwnSymbol() && field.getSymbol(x, y + 1) == ' ') {
                            result = field.add(x, y + 1, getOwnSymbol());
                            break;
                        }
                    } else if (field.getSymbol(x, y) == ' ') {
                        if (x + 2 < field.getField().length && field.getSymbol(x + 1, y) == getOwnSymbol() && field.getSymbol(x + 2, y) == getOwnSymbol()) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        } else if (x + 2 < field.getField().length && field.getSymbol(x + 2, y) == getOwnSymbol() && field.getSymbol(x + 1, y) == getOwnSymbol()) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && field.getSymbol(x, y + 1) == getOwnSymbol() && field.getSymbol(x, y + 2) == getOwnSymbol()) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        } else if (y + 2 < field.getField().length && field.getSymbol(x, y + 2) == getOwnSymbol() && field.getSymbol(x, y + 1) == getOwnSymbol()) {
                            result = field.add(x, y, getOwnSymbol());
                            break;
                        }
                    }
                }
            }
        }
        if (!result) {
            if (field.getSymbol(0, 0) == getOwnSymbol() && field.getSymbol(1, 1) == getOwnSymbol() && field.getSymbol(2, 2) == ' ') {
                result = field.add(2, 2, getOwnSymbol());
            } else if (field.getSymbol(0, 0) == getOwnSymbol() && field.getSymbol(2, 2) == getOwnSymbol() && field.getSymbol(1, 1) == ' ') {
                result = field.add(1, 1, getOwnSymbol());
            } else if (field.getSymbol(2, 2) == getOwnSymbol() && field.getSymbol(1, 1) == getOwnSymbol() && field.getSymbol(0, 0) == ' ') {
                result = field.add(0, 0, getOwnSymbol());
            } else if (field.getSymbol(1, 1) == getOwnSymbol() && field.getSymbol(2, 2) == getOwnSymbol() && field.getSymbol(0, 0) == ' ') {
                result = field.add(0, 0, getOwnSymbol());
            } else if (field.getSymbol(2, 0) == getOwnSymbol() && field.getSymbol(1, 1) == getOwnSymbol() && field.getSymbol(0, 2) == ' ') {
                result = field.add(0, 2, getOwnSymbol());
            } else if (field.getSymbol(0, 2) == getOwnSymbol() && field.getSymbol(1, 1) == getOwnSymbol() && field.getSymbol(2, 0) == ' ') {
                result = field.add(2, 0, getOwnSymbol());
            } else if (field.getSymbol(0, 2) == getOwnSymbol() && field.getSymbol(2, 0) == getOwnSymbol() && field.getSymbol(1, 1) == ' ') {
                result = field.add(1, 1, getOwnSymbol());
            }
        }
        if (!result) {
            result = commonMove(field);
        }
        return result;
    }

    private boolean commonMove(Field field) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (!result) {
                for (int y = 0; y < field.getField()[x].length; y++) {
                    if (isEnemySymbol(field.getSymbol(x, y))) {
                        if (field.getField().length > x + 2 && field.getSymbol(x + 1, y) == ' ' && field.getSymbol(x + 2, y) == ' ') {
                            result = field.add(x + 1, y, getOwnSymbol());
                            break;
                        }
                        if (0 == x - 2 && field.getSymbol(x - 1, y) == ' ' && field.getSymbol(x - 2, y) == ' ') {
                            result = field.add(x - 1, y, getOwnSymbol());
                            break;
                        } else if (field.getField()[x].length > y + 2 && field.getSymbol(x, y + 1) == ' ' && field.getSymbol(x, y + 2) == ' ') {
                            result = field.add(x, y + 1, getOwnSymbol());
                            break;
                        } else if (y - 2 == 0 && field.getSymbol(x, y - 1) == ' ' && field.getSymbol(x, y - 2) == ' ') {
                            result = field.add(x, y - 1, getOwnSymbol());
                            break;
                        } else if (y - 2 == 0 && x - 2 == 0 && field.getSymbol(x - 1, y - 1) == ' ' && field.getSymbol(x - 1, y - 2) == ' ') {
                            result = field.add(x - 1, y - 1, getOwnSymbol());
                            break;
                        } else if (field.getField()[x].length > y + 2 && x - 2 == 0 && field.getSymbol(x - 1, y + 1) == ' ' && field.getSymbol(x - 1, y + 2) == ' ') {
                            result = field.add(x - 1, y + 1, getOwnSymbol());
                            break;
                        } else if (y - 2 == 0 && field.getField().length > x + 2 && field.getSymbol(x + 1, y - 1) == ' ' && field.getSymbol(x + 2, y - 2) == ' ') {
                            result = field.add(x - 1, y - 1, getOwnSymbol());
                            break;
                        }
                    }
                }
            }
        }
        if (!result) {
            result = randomMove(field);
        }
        return result;
    }
}

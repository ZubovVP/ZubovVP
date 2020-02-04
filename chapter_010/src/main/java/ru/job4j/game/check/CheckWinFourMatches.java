package ru.job4j.game.check;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.01.2020.
 */
public class CheckWinFourMatches extends CheckWin {


    public CheckWinFourMatches(Field field) {
        super(field);
    }

    /**
     * Check win for four matches.
     *
     * @param symbol - checkable symbol.
     * @return - result.
     */
    @Override
    public boolean checkWin(Character symbol) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            if (result) {
                break;
            }
//CHECKSTYLE:OFF
            for (int y = 0; y < field.getField().length; y++) {
                if (field.getField()[x][y] == symbol) {
                    if ((field.getField().length > x + 3 && field.getField()[x].length > y + 3 && field.getField()[x + 1][y + 1] == symbol && field.getField()[x + 2][y + 2] == symbol && field.getField()[x + 3][y + 3] == symbol) ||
                            (x - 3 >= 0 && field.getField()[x].length > y + 3 && field.getField()[x - 1][y + 1] == symbol && field.getField()[x - 2][y + 2] == symbol && field.getField()[x - 3][y + 3] == symbol) ||
                            (field.getField().length > x + 3 && field.getField()[x + 1][y] == symbol && field.getField()[x + 2][y] == symbol && field.getField()[x + 3][y] == symbol) ||
                            (field.getField()[x].length > y + 3 && field.getField()[x][y + 1] == symbol && field.getField()[x][y + 2] == symbol && field.getField()[x][y + 3] == symbol)) {
                        result = true;
                        break;
                    }
                    //CHECKSTYLE:ON
                }
            }
        }
        return result;
    }
}

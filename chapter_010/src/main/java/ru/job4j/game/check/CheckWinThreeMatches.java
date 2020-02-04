package ru.job4j.game.check;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.01.2020.
 */
public class CheckWinThreeMatches extends CheckWin {


    public CheckWinThreeMatches(Field field) {
        super(field);
    }

    /**
     * Check win for three matches.
     *
     * @param symbol - checkable symbol.
     * @return - result.
     */
    @Override
    public boolean checkWin(Character symbol) {
        boolean result = false;
        for (int x = 0; x < field.getField().length; x++) {
            for (int y = 0; y < field.getField()[x].length; y++) {
                //CHECKSTYLE:OFF
                if ((this.field.getField()[x][0] == symbol && this.field.getField()[x][1] == symbol && this.field.getField()[x][2] == symbol) ||
                        (this.field.getField()[0][x] == symbol && this.field.getField()[1][x] == symbol && this.field.getField()[2][x] == symbol)) {
                    result = true;
                    break;
                }

            }
        }
        if (!result && (this.field.getField()[0][0] == symbol && this.field.getField()[1][1] == symbol && this.field.getField()[2][2] == symbol) ||
                (this.field.getField()[2][0] == symbol && this.field.getField()[1][1] == symbol && this.field.getField()[0][2] == symbol)) {
            result = true;
        }
        //CHECKSTYLE:ON
        return result;
    }
}

package ru.job4j.game.view;

import ru.job4j.game.Field;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.01.2020.
 */
public class ShowField {

    /**
     * Show field with symbols.
     *
     * @param field - field.
     * @return - string of field.
     */
    public String show(Field field) {
        StringBuilder sb = new StringBuilder();
        for (int y = field.getSize() - 1; y >= 0; y--) {
            sb.append("\n");
            sb.append(y);
            for (int x = 0; x < field.getSize(); x++) {
                if (field.getField()[y][x] != null) {
                    sb.append(" ");
                    sb.append(field.getField()[y][x]);
                    sb.append(" |");

                } else {
                    sb.append("   |");
                }
            }
        }
        sb.append("\n");
        for (int x = 0; x < field.getField().length; x++) {
            sb.append("  ");
            sb.append(x);
            sb.append(" ");
        }
        return sb.toString();
    }
}

package ru.job4j.game.view;

import org.junit.Test;
import ru.job4j.game.Field;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.02.2020.
 */
public class ShowFieldTest {

    @Test
    public void testIsEmptyField() {
        Field field = new Field(3);
        ShowField show = new ShowField();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(2);
        sb.append("   |   |   |\n");
        sb.append(1);
        sb.append("   |   |   |\n");
        sb.append(0);
        sb.append("   |   |   |\n");
        sb.append("  0   1   2 ");
        assertThat(show.show(field), is(sb.toString()));
    }

    @Test
    public void addSymbolInTheFieldCheckShowField() {
        Field field = new Field(3);
        field.add(0, 0, 'X');
        field.add(0, 1, 'O');
        ShowField show = new ShowField();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(2);
        sb.append("   |   |   |\n");
        sb.append(1);
        sb.append("   |   |   |\n");
        sb.append(0);
        sb.append(" X | O |   |\n");
        sb.append("  0   1   2 ");
        assertThat(show.show(field), is(sb.toString()));
    }
}
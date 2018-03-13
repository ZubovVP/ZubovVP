package ru.job4j.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    // получаем ссылку на стандартный вывод в консоль.
   private final PrintStream stdout = System.out;
    // Создаем буфер для хранения вывода.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        //Заменяем стандартный вывод на вывод в пямять для тестирования.
        System.setOut(new PrintStream(this.out));

    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }
    @Test
    public void whenDrawSquare() {
        // выполняем действия пишушиее в консоль.
        new Paint().draw(new Square());
        // проверяем результат вычисления
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                                .append("++++++")
                                .append("+    +")
                                .append("+    +")
                                .append("++++++").append(System.lineSeparator()).toString()));
        // возвращаем обратно стандартный вывод в консоль.
    }
    @Test
    public void whenDrawTriangle() {
        // выполняем действия пишушиее в консоль.
        new Paint().draw(new Triangle());
        // проверяем результат вычисления
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                                                .append("    +     ")
                                                .append("   + +    ")
                                                .append("  +   +   ")
                                                .append(" +      + ")
                                                .append(" ++++++++ ").append(System.lineSeparator()).toString()));
    }
    }
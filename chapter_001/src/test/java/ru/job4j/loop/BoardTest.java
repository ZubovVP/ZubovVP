package ru.job4j.loop;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {
    /**
	* Test add.
	*/
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        StringBuilder result = board.paint(3, 3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x%s x %sx x", line, line);
        assertThat(result, is(expected));
 }
    /**
	* Test add.
	*/
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
		//Создаём объект
        Board board2 = new Board();
		//Отправляем переменные 5 и 4 в метод и инициализируем переменную result переменной которую получаем из метода
		StringBuilder result = board2.paint(5, 4);
		//Присваеваем переменной line пропуск троки
		final String line = System.getProperty("line.separator");
		//Указываем ожидаемый результат
		String expected = String.format("x x x%s x x %sx x x%s x x %s x x ", line, line, line, line, line);
		//Сравниваем ожидаемый результат с тем что выходит из метода
		assertThat(result, is(expected));
    }
}
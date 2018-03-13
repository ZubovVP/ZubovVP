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
        String result = board.paint(3, 3);

        final String line = System.getProperty("line.separator");
        String expected = "X X\n X \nX X\n";
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
		String result = board2.paint(5, 4);
		//Присваеваем переменной line пропуск троки
		final String line = System.getProperty("line.separator");
		//Указываем ожидаемый результат
		String expected = "X X X\n X X \nX X X\n X X \n";
		//Сравниваем ожидаемый результат с тем что выходит из метода
		assertThat(result, is(expected));
    }
}
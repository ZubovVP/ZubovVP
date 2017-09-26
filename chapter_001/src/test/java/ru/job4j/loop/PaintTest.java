package ru.job4j.loop;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    /**
	* Test add.
	*/
    public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        String result = paint.piramid(2);
        String expected = String.format(" ^ %s^^^", System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }
    /**
	* Test add.
	*/
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
       //Создадим объект
	   Paint paint = new Paint();
	   //Отправляем переменную 3 в метод и инициализируем переменную result переменной которую получаем из метода
        String result = paint.piramid(3);
		//Вводим ожидаемый результат от метода result
        String expected = String.format("  ^  %s ^^^ %s^^^^^", System.getProperty("line.separator"), System.getProperty("line.separator"));
		//Проводим проверку ожидаемого результата и то что получается в методе
        assertThat(result, is(expected));
    }
}
package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {
    @Test
    public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        String result = paint.piramid(2);
        String expected = String.format(" ^ %s^^^", System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }

    @Test
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
       //Создадим объект
	   Paint paint = new Paint();
	   //Отправляем переменную 3 в метод и инициализируем переменную result переменной которую получаем из метода
        String result = paint.piramid(3);
		//Вводим ожидаемый результат от метода result
        String expected = String.format("  ^  %s ^^^ %s^^^^^", System.getProperty("line.separator") , System.getProperty("line.separator"));
		//Проводим проверку ожидаемого результата и то что получается в методе
        assertThat(result, is(expected));
    }
    }
}
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
public class FactorialTest {
	@Test
	public void whenNumberFive() {
		/**
		* Test add.
		*/
		//Создадим объект.
		Factorial factorialFirst = new Factorial();
		// Отправим перменную на проверку и при прохождении проверки вычисляем факториал.
		int result = factorialFirst.calc(5);
		//Проверяем результат и ожидаемое значение.
		assertThat(result, is (120));
	}
	@Test
	public void whenNumberZero() {
		/**
		* Test add.
		*/
		//Создадим объект и внесём.
		Factorial factorialSecond = new Factorial();
		// Отправим перменную на проверку и при прохождении проверки вычисляем факториал.
		int result = factorialSecond.calc(0);
		//Проверяем результат и ожидаемое значение.
		assertThat(result, is(1));
	}
}
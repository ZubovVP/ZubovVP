package ru.job4j.loop;

import static org.hamrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {
	/**
	* Test add.
	*/
	public void whenNumberFive() {
		/**
		* Test add.
		*/
		//Создадим объект.
		Factorial factorialFirst = new Factorial();
		// Отправим перменную на проверку и при прохождении проверки вычисляем факториал.
		int result = factorialFirst.exist(5);
		//Проверяем результат и ожидаемое значение.
		assertThat(result, is(120));
	}
/**
 * Test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
	public void whenNumberZero() {
		/**
		* Test add.
		*/
		//Создадим объект и внесём.
		Factorial factorialSecond = new Factorial();
		// Отправим перменную на проверку и при прохождении проверки вычисляем факториал.
		int result = factorialSecond.exist(0);
		//Проверяем результат и ожидаемое значение.
		assertThat(result, is(1));
	}
}
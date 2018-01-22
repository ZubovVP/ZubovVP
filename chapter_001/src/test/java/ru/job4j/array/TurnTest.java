package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
	/**
	* Test.
	*
	* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
	* @version $Id$
	* @since 0.1
	*/
public class TurnTest {
	/**
	* Test add.
	*/
	@Test
	public void whenOddNumberOfVariablesInTheArray() {
		Turn turn = new Turn();
		//Задаём произвольный массив(нечётный)
		int[] numbers = {1, 2, 3, 4, 5};
		//Отправляем заданный массив в метод
		 int[] result = turn.back(numbers);
		 //Указываем ожидаемый результат
		 int[] expected = {5, 4, 3, 2, 1};
		 //Сравниваем полученный и ожидаемый результат
		 assertThat(result, is(expected));
	}
	/**
	* Test add.
	*/
	public void whenAnEvenNumbersOfVariablesInTheArray() {
		Turn turn = new Turn();
		//Задаём произвольный массив(чётный)
		int[] numbers = {1, 2, 3, 4};
		//Отправляем заданный массив в метод
		 int[] result = turn.back(numbers);
		 //Указываем ожидаемый результат
		 int[] expected = {4, 3, 2, 1};
		 //Сравниваем полученный и ожидаемый результат
		 assertThat(result, is(expected));
	}
}
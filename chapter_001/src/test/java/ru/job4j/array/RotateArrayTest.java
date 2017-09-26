package ru.job4j.array;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
	/**
	* Test.
	*
	* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
	* @version $Id$
	* @since 0.1
	*/
public class RotateArrayTest {
	/**
	* Test add.
	*/
	public void whenArrayThreeOnThreeElements() {
		//Задаём произвольный массив
		int[] [] numbers = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		//Отправляем заданный массив в метод
		 int[] [] result = numbers.rotate();
		 //Указываем ожидаемый результат
		 int[] expected = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
		 //Сравниваем полученный и ожидаемый результат
		 assertThat(result, is(expected));
		 }
	/**
	* Test add.
	*/
	public void whenArrayTwoOnTwoElements() {
		//Задаём произвольный массив
		int[] [] numbers = {{1, 2}, {3, 4}};
		//Отправляем заданный массив в метод
		 int[] [] result = numbers.rotate();
		 //Указываем ожидаемый результат
		 int[] [] expected = {{3, 1}, {4, 2}};
		 //Сравниваем полученный и ожидаемый результат
		 assertThat(result, is(expected));
	}
}
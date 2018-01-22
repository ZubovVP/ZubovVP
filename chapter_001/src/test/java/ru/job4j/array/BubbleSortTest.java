package ru.job4j.array;

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
public class BubbleSortTest {
	/**
	* Test add.
	*/
	@Test
	public void whenSortArrayTenElements() {
		BubbleSort bubbleSort = new BubbleSort();
		//Задаём произвольный массив
		int[] numbers = {1, 5, 4, 2, 3, 9, 7, 8, 0, 6};
		//Отправляем заданный массив в метод
		int[] result = bubbleSort.sort(numbers);
		//Указываем ожидаемый результат
		int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		//Сравниваем полученный и ожидаемый результат
		assertThat(result, is(expected));
	}
}
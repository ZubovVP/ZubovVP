package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest{
	@Test
	public void whenSortArrayTenElements(){
		//Задаём произвольный массив
		int [] numbers = {1, 5, 4, 2, 3, 9, 7, 8, 0, 6};
		//Отправляем заданный массив в метод
		 int [] result =numbers.sort();
		 //Указываем ожидаемый результат
		 int [] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		 //Сравниваем полученный и ожидаемый результат
		 assertThat(result, is(expected));
	
}
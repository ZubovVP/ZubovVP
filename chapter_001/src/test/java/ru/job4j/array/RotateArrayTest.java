package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RotateArrayTest{
	@Test
	public void whenArrayThreeOnThreeElements(){
		//Задаём произвольный массив
		int [] [] numbers = {{1,2,3},{4,5,6},{7,8,9}};
		//Отправляем заданный массив в метод
		 int [] [] result =numbers.rotate();
		 //Указываем ожидаемый результат
		 int [] expected = {{7,4,1},{8,5,2},{9,6,3}};
		 //Сравниваем полученный и ожидаемый результат
		 assertThat(result, is(expected));
		 }
	public void whenArrayTwoOnTwoElements(){
		//Задаём произвольный массив
		int [] [] numbers = {{1,2},{3,4}};
		//Отправляем заданный массив в метод
		 int [] [] result =numbers.rotate();
		 //Указываем ожидаемый результат
		 int [] expected = {{3,1},{4,2}};
		 //Сравниваем полученный и ожидаемый результат
		 assertThat(result, is(expected));
	}
}
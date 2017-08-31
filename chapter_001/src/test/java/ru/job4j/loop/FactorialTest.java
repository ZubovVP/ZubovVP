package ru.job4j.loop;	

import org.junit.Test;

import static org.hamrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

public void whenNumberFive() {
    //Создадим объект.
    Factorial factorialFirst = new Factorial();
    // Отправим перменную на проверку и при прохождении проверки вычисляем факториал.
    int result = factorialFirst.exist(5);
    //Проверяем результат и ожидаемое значение.
	assertThat(result, is(120));
 }
 public void whenNumberZero() {
    //Создадим объект и внесём.
    Factorial factorialSecond = new Factorial();
    // Отправим перменную на проверку и при прохождении проверки вычисляем факториал.
    int result = factorialSecond.exist(0);
    //Проверяем результат и ожидаемое значение.
	assertThat(result, is(1));
 }
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
public class RotateArrayTest {
    /**
     * Test add.
     */
    @Test
    public void whenArrayThreeOnThreeElements() {
        RotateArray rotateArray = new RotateArray();
        //Задаём произвольный массив
        int[][] numbers = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //Отправляем заданный массив в метод
        int[][] result = rotateArray.rotate(numbers);
        //Указываем ожидаемый результат
        int[][] expected = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        //Сравниваем полученный и ожидаемый результат
        assertThat(result, is(expected));
    }

    /**
     * Test add.
     */
    @Test
    public void whenArrayTwoOnTwoElements() {
        RotateArray rotateArray = new RotateArray();
        //Задаём произвольный массив
        int[][] numbers = {{1, 2}, {3, 4}};
        //Отправляем заданный массив в метод
        int[][] result = rotateArray.rotate(numbers);
        //Указываем ожидаемый результат
        int[][] expected = {{3, 1}, {4, 2}};
        //Сравниваем полученный и ожидаемый результат
        assertThat(result, is(expected));
    }
}
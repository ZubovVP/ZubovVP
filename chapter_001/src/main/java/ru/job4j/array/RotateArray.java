package ru.job4j.array;
/**
 * RotateArray.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class RotateArray {
	 /**
     * Метод позволяет повернуть массив по часовй стрелке на 90 градусов.
     * @param array - исходный массив
	 * @return rotate - обработынный массив
     */
	public int[][] rotate(int[][] array) {
		//Создадим массив, у квадратного массива размерность строк совпадает с размерностью столцов
		int[][] arrayNew  = new int[array.length][array.length];
		//Данный цикл позволяет перебирать столбцы в массиве
			for (int x = 0; x < array.length; x++) {
				//Данный цикл позволяет перебирать стоки в массиве
				for (int y = 0; y < array[x].length; y++) {
					//Дальше идут условия, при которых переменные из одного массива заносятся в другой
					if (x == 0) {
						arrayNew [y] [array[x].length - 1] =  array [x] [y];
					} else if (x == array[x].length - 1) {
							arrayNew[y][0] = array[x][y];
					} else {
						arrayNew [y] [x] = array [x] [y];
					}
				}
			}
			// Отправляем массив обратно
			return arrayNew;
	}
}
package ru.job4j.array;
/**
 * BubbleSort.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {
	 /**
     * Метод позволяющий переставить даныые по возрастанию.
     * @param array - исходный массив
	 * @return sort - отсортированный массив
     */
	public int[] sort(int[] array) {
			//Данный цикл отвечает сколько раз нужно использовать операцицию перестановки для того чтобы на 100% достигнуть желаемого результата
	for (int x = 0; x < array.length; x++) {
		//Данный цикл перебирает каждое число в массиве
			for (int y = 0; y < array.length - 1; y++) {
				//Данное условие производить замену переменных местами
				if (array[y] > array[y + 1]) {
					int q = array[y];
					array[y] = array[y + 1];
					array[y + 1] = q;
				}
			}
		}
		return array;
	}
}
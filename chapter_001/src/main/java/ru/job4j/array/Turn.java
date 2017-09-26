package ru.job4j.array;
/**
 * Turn.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Turn {
	 /**
     * Метод позволяет перевернуть массив.
     * @param array - исходный массив
	 * @return back - перевёрнутый массив
     */
	public int[] back(int[] array) {
		//Зададим условия для массива с чётным количеством переменных
		if (array.length % 2 == 0) {
			for (int x = 0; x < array.length / 2; x++) {
				//Присвоим переменной значения из масива
				//(чтобы потом при обмене значений не терялись переменные)
				int q = array[x];
				int w = array[array.length - (x + 1)];
				//Производим замену переменных в массиве
			array[array.length - (x + 1)] = q;
			array [x] = w;
			}
		} else {
			//Зададим условия для массива с нечётным количеством переменных
			for (int y = 0; y < array.length / 2; y++) {
			//тут осознано интовую перменную делю на 2 и теряю остаток,
			//на работу программы это не повлияет.
			//Присвоим переменной значения из масива
			//(чтобы потом при обмене значений не терялись переменные)
				int q = array[y];
				int w = array[array.length - (y + 1)];
				//Производим замену переменных в массиве
				array[array.length - (y + 1)] = q;
				array[y] = w;
			}
		}	//Возращем массив
			return array;
		}
	}
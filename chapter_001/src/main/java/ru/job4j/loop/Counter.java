package ru.job4j;
/**
 * Counter.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Counter {
	 /**
     * Программа позволяет рассчитать сумму чётных чисел в заданном интервале.
     * @param start - начальное число интервала
	 * @param finish - конечное число интервала
	 * @return add - расчитанный результат
     */
	public int add(int start, int finish) {
		int summ = 0;
		for (int first = finish; first <= finish; first++) {
			if (first % 2 == 0) {
				summ += first;
			}
	} return summ; }
}
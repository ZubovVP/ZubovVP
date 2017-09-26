package ru.job4j.loop;
/**
 * Factorial.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
	 /**
     * Программа предназначенна для вычисления факториала.
     * @param n - число из которого вычисляется факториал
	 * @return calc - результат факториала
     */
	public int calc(int n) {
		int factorial = 0;
		if (this.exist(n)) {
			if (n > 0) {
				for (int x = 1; x <= n; x++) {
					factorial *= x;
				}
			} else {
				factorial = 1;
			}
			return factorial;
		}
}
 /**
     * Проверка вводимого числа.
     * @param n - число из которого вычисляется факториал
	 * @return exist - результат проверки
     */
private  boolean exist(int n) {
		return n > 0;
}
}

package ru.job4j;

/**
 * Max.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Max {
	 /**
     * Программа позволяет определять максимальное число по значению из двух чисел.
     * @param first - первое число
	 * @param second - второе число
	 * @return max - вывод максимального числа
     */
	public int max(int first, int second) {
          return first > second ? first : second;
      }
	   /**
     * Программа позволяет определять максимальное число по значению из трёх чисел.
     * @param first - первое число
	 * @param second - второе число
	 * @param third - третье число
	 * @return max - вывод максимального числа
     */
	public int max(int first, int second, int third) {
		int max = first > second ? first : second;
		return max > third ? max : third;
	}
}
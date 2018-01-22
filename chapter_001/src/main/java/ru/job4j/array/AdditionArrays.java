package ru.job4j.array;
/**
 * AdditionArrays.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class AdditionArrays {
	/**
    * Метод позволяет складывать два массива и сортировать значения по возрастанию.
    * @param first - первый массив
	* @param second - второй массив
	* @return additionsAndSort - результат проверки
    */
	public int[] additionsAndSort(int[] first, int[] second) {
        int[] sum = new int[first.length + second.length];
        int max = first.length > second.length ? first.length : second.length;
        for (int inside = 0; inside < max; inside++) {
            if (first.length != second.length && inside == max - 1) {
                sum[inside * 2] = first.length > second.length ? first[inside] : second[inside];
            } else if (first[inside] < second[inside]) {
                sum[inside * 2] = first[inside];
                sum[inside * 2 + 1] = second[inside];
            } else {
                sum[inside * 2] = second[inside];
                sum[inside * 2 + 1] = first[inside];
            }
        }
        return sum;
    }
}
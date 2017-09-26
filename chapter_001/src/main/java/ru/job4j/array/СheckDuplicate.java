package ru.job4j.array;
/**
 * CheckDuplicate.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class CheckDuplicate {
	/**
    * Метод позволяет определять что одно слово находиться в другом.
    * @param origin - первой слово с которомы будет проводится проверка
	* @param sub - второе слово которое буде сверятся с первым
	* @return contains - результат проверки
    */
	public boolean contains(String origin, String sub) {
	boolean check = false;
        char[] arrayOrigin = origin.toCharArray();
        char[] arraySub = sub.toCharArray();
       outer: for (int outsaid = 0; outsaid < arraySub.length; outsaid++) {
           for (int inside = 0; inside < arrayOrigin.length; inside++) {
               if (arraySub[outsaid] == arrayOrigin[inside]) {
                   check = true;
                   continue outer;
               } else {
                   check = false;
				   break outer;
               }
            }
			return check;
		}
	}
 }
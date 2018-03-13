package ru.job4j.loop;
/**
 * Board.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class Board {
	/**
     * Метод позволяет заполнять объект " " и "х".
     * @param width - Ширина доски
	 * @param height - Высота доски
	 * @return paint - вывод доски
     */
	public String paint(int width, int height) {
		StringBuilder  paint = new StringBuilder();
		 for (int x = 1; x <= height; x++) {
			 for (int y = 1; y <= width; y++) {
				 if ((x + y) % 2 == 0) {
					 paint.append("X");
				 } else {
					 paint.append(" ");
				}
			}
		 paint.append("\n");
		}
		return paint.toString();
	}
}
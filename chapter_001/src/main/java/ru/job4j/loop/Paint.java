package ru.job4j.loop;
/**
 * Paint.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Paint {
	 /**
     * Программа позволяет рисовать пирамиду в псевдографике.
     * @param h - высота пирамиды
	 * @return piramid - вывод пирамиды
     */
	public String piramid(int h) {
		//Создадим объектную переменную
		StringBuilder pyramid = new StringBuilder();
		//Указываем условия для определия высоты пирамиды
		for (int x = 1; x <= h; x++) {
			//Указываем условия для определения ширины пирамиды на каждом уровене(высоте)
			for (int y = 1; y <= ((h - 1) * 2 + 1); y++) {
				//Описываем условия заполнения пирамиды(левой части)
				if (y < h) {
					if (x + y > h) {
						pyramid.append("^");
					} else {
						pyramid.append(" ");
					}
					//Описываем условия заполнения пирамиды (центральной и правой части)
				} else {
					if (x + h > y) {
						pyramid.append("^");
					} else {
						pyramid.append(" ");
					}
				}
			}
			pyramid.append(System.getProperty("line.separator"));
		}

		return pyramid.toString();
	}
}
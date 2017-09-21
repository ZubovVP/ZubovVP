package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplecate
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

 public class ArrayDuplicate {

	 /**
     * Main.
     * @param array - array.
     */

	public String[] remove(String[] array) {
		int uniqueness = array.length;
		for (int outside = 0; outside < uniqueness; outside ++){
			for (int inside = outside + 1; inside < uniqueness; inside ++ ){
				if (array[outside].equals(array[inside])) {
					String name = array[inside];
					array[inside] = array[uniqueness - 1];
					array[uniqueness - 1] = name;
					uniqueness --;
					inside -- ;
				}
			}
		}
		return Arrays.copyOf(array,uniqueness);
	}
		
}
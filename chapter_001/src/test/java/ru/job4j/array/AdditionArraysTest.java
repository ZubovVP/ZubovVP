package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
* @version $Id$
* @since 0.1
*/
public class AdditionArraysTest {
	/**
	* Test add.
	*/
	@Test
	public void whenFirstArrayplusSecondArray() {
		int[] first = {1, 3, 5, 7};
		int[] second = {2, 4, 6};
		AdditionArrays addition = new AdditionArrays();
		int[] result = addition.additionsAndSort(first, second);
		int[] expected = {1, 2, 3, 4, 5, 6, 7};
		assertThat(result, is(expected));
	}
}
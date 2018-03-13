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
 public class ArrayDuplicatesTest {
	/**
	* Test add.
	*/
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
		String[] original = {"Раз", "Три", "Два", "Три", "Раз", "Четыре"};
		ArrayDuplicate duplicate = new ArrayDuplicate();
		String[] result = duplicate.remove(original);
		String[] expected = {"Раз", "Три", "Два", "Четыре"};
		assertThat(result, is(expected));
	}
}

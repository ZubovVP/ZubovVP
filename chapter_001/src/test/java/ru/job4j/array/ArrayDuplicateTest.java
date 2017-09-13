package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static java.util.Arrays;

/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
* @version $Id$
* @since 0.1
*/

public class ArrayDuplicatesTest{
	@Test
/**
* Test add.
*/
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate(){
		String[] array = {"Раз", "Три", "Два", "Три", "Раз", "Четыре"};
		String [] result = array.remove();
		String[] expected = {"Раз","Два","Три","Четыре"};
		assertThat(result, is (expected));
	}
}

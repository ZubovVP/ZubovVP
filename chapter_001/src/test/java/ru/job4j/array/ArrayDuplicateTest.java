package ru.job4j.array;

import org.junit.Test;

import javax.swing.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
* @version $Id$
* @since 0.1
*/

public class ArrayDuplicatesTest{
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate(){
		String[] original = {"Раз", "Три", "Два", "Три", "Раз", "Четыре"};
		Box duplicate = new Box();
		String[] result = duplicate.remove(original);
		String[] expected = {"Раз","Два","Три","Четыре"};
		assertThat(result, is (expected));
	}
}

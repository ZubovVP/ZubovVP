package ru.job4j.specialty;
import org.junit.Test.

import javax.swing.*.

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
* @version $Id$
* @since 0.1
*/
public class TheacherTest {
	/**
	* Test add.
	*/
	public void whenMakeObjectTheacher() {
		Theacher theac = new Theacher(25, true, false);
		boolean resultOne = theac.requirementsForWork();
		boolean expectedOne = false;
		assertThat(resultOne, is(expectedOne));
		boolean resultTwo = doc.treatmentOfPeople();
		boolean expectedTwo = true;
		assertThat(resultTwo, is(expectedTwo));
	}
}
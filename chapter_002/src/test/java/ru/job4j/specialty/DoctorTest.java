package ru.job4j.specialty;
import org.junit.Test.

/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
* @version $Id$
* @since 0.1
*/
public class DoctorTest {
	/**
	* Test add.
	*/
	public void whenMakeObjectDoctor() {
		Doctor doc = new Doctor(18, true, true);
		boolean resultOne = doc.requirementsForWork();
		boolean expectedOne = true;
		assertThat(resultOne, is(expectedOne));
		boolean resultTwo = doc.treatmentOfPeople();
		boolean expectedTwo = true;
		assertThat(resultTwo, is(expectedTwo));
	}
}
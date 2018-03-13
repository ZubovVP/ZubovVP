package ru.job4j.specialty;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
* @version $Id$
* @since 0.1
*/
public class DoctorTest extends Profession {
	/**
	* Test add.
	*/
	@Test
	public void whenMakeObjectDoctorAndMakeObjectPacient() {
		Doctor doc = new Doctor("Василий", 25, "лечит");
		Profession pacient = new Profession("Игнат");
		String result = doc.heal(pacient);
		String expected = "Василий лечит Игнатa";
		assertEquals(result, expected);

	}
}
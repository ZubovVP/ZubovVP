package ru.job4j.specialty;
import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

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
		Doctor pacient = new Doctor("Игнат");
		String result = doc.heal(Doctor pacient);
		String expected = "Василий лечит Игната";
		assertThat(result, is(expected));
	}
}
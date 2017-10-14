package ru.job4j.specialty;
import org.junit.Test;
import org.junit.Test.

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

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
	@Test
	public void whenMakeObjectTeacherAndMakeObjectHuman() {
		Profession thea = new Theacher("Анастасия", 22, "учит");
		Profession hum = new Theacher("Александр");
		String result = thea.teach(Profession hum);
		String expected = "Анастасия учит Александра";
		assertThat(result, is(expected));
	}
}
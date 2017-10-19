package ru.job4j.specialty;
import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static junit.framework.TestCase.assertEquals;

/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
* @version $Id$
* @since 0.1
*/
public class TeacherTest {
	/**
	* Test add.
	*/
	@Test
	public void whenMakeObjectTeacherAndMakeObjectHuman() {
		Teacher thea = new Teacher("Анастасия", 22, "учит");
		Profession man = new Profession("Александр");
		String result = thea.teach(man);
		String expected = "Анастасия учит Александра";
		assertEquals(result, is(expected));
	}
}
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
public class EngineerTest {
	/**
	* Test add.
	*/
	@Test
	public void whenMakeObjectEngineerAndMakeObjectHuman() {
		Engineer eng = new Engineer("Виталий", 40, "расчёт");
		Profession man = new Profession("Михаил");
		String result = eng.calculate(man);
		String expected = "Виталий делает расчёт для Михаила";
		assertEquals(result, expected);
	}
}
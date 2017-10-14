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
public class EngineerTest {
	/**
	* Test add.
	*/
	@Test
	public void whenMakeObjectEngineerAndMakeObjectHuman() {
		Profession eng = new Engineer("Виталий", 40, "расчёт");
		Profession hum = new Human("Михаил");
		String result = eng.calculate(Human hum);
		String expected = "Виталий делает расчёт для Михаила";
		assertThat(result, is(expected));
	}
}
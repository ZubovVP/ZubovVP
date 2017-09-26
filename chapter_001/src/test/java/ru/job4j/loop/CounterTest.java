package ru.job4j.loop;

import static org.hamrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {
	/**
	* Test add.
	*/
	public void whenStartTwoFinishTen() {
		Sum summa = new Sum();
		int result = summa.add(2, 10);
		assertThat(result, is(30));
	}
}
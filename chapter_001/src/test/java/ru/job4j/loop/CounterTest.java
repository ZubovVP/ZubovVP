package ru.job4j.loop;

import org.junit.Test;
import ru.job4j.Counter;

import static org.hamcrest.core.Is.is;
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
	@Test
	public void whenStartTwoFinishTen() {
		Counter summa = new Counter();
		int result = summa.add(2, 10);
		assertThat(result, is(30));
	}
}
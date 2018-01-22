package ru.job4j.max;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {
	/**
	 * Test add.
	 */
	@Test
	public void whenFirstLessSecond() {
		Max maxim = new Max();
		int result = maxim.max(1, 2);
		assertThat(result, is(2));
	}

	/**
	 * Test add.
	 */
	@Test
	public void whenFirstMoreSecond() {
		Max maxim = new Max();
		int result = maxim.max(5, 2);
		assertThat(result, is(5));
	}

	/**
	 * Test add.
	 */
	@Test
	public void whenFirstSameSecond() {
		Max maxim = new Max();
		int result = maxim.max(3, 3);
		assertThat(result, is(3));
	}

	/**
	 * Test add.
	 */
	@Test
	public void whenFirstMoreSecondMoreThird() {
		Max maxim = new Max();
		int result = maxim.max(5, 2, 4);
		assertThat(result, is(5));
	}

	/**
	 * Test add.
	 */
	@Test
	public void whenSecondMoreFirstMoreThird() {
		Max maxim = new Max();
		int result = maxim.max(1, 9, 3);
		assertThat(result, is(9));
	}

	/**
	 * Test add.
	 */
	@Test
	public void whenThirdMoreFirstMoreSecon() {
		Max maxim = new Max();
		int result = maxim.max(1, 1, 5);
		assertThat(result, is(5));
	}

	/**
	 * Test add.
	 */
	@Test
	public void whenFirstSameSecondSameThird() {
		Max maxim = new Max();
		int result = maxim.max(4, 4, 4);
		assertThat(result, is(4));
	}
}
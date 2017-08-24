package ru.job4j;

import org.junit.Test;

import static org.hamrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
	@Test
	public void whenFirstLessSecond() {
		Max maxim = new Max();
		int result = maxim.max(1, 2);
		assertThat(result, is(2));
		}
	public void whenFirstMoreSecond() {
		Max maxim = new Max();
		int result = maxim.max(5, 2);
		assertThat(result, is(5));
		}
	public void whenFirstSameSecond() {
		Max maxim = new Max();
		int result = maxim.max(3, 3);
		assertThat(result, is(3));
		}
}
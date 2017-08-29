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
	public void whenFirstMoreSecondMoreThird() {
		Max maxim = new Max();
		int result = maxim.max(5, 2, 4);
		assertThat(result, is(5));
		}
	public void whenSecondMoreFirstMoreThird() {
		Max maxim = new Max();
		int result = maxim.max(1, 9, 3);
		assertThat(result, is(9));
		}
	public void whenThirdMoreFirstMoreSecon() {
		Max maxim = new Max();
		int result = maxim.max(1, 1, 5);
		assertThat(result, is(5));
		}
	public void whenFirstSameSecondSameThird() {
		Max maxim = new Max();
		int result = maxim.max(4, 4, 4);
		assertThat(result, is(4));
}
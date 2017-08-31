package ru.job4j.loop;	

import org.junit.Test;

import static org.hamrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
	@Test
	public void whenStartTwoFinishTen(){
		Sum summa = new Sum();
		int result = summa.add(2, 10);
		assertThat(result, is(30));
	}
}
package ru.job4j.start;

public interface Input {
	String ask(String question);
	
	int ask(String quesion, int[] range);
}
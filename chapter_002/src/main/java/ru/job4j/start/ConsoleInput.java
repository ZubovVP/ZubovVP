package ru.job4j.start;

import java.util.*;

public class ConsoleInput implements InputInt, InputString {

	private Scanner scanner = new Scanner(System.in);
	private int number;
	private String answer;

	public int askInt(String question) {
		System.out.println(question);
		number = scanner.nextInt();
		return number;
	}

	public String askString(String question) {
		System.out.println(question);
		answer = scanner.nextLine();
			return answer;
	}
}
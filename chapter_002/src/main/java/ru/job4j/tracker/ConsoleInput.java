package ru.job4j.tracker;

import java.util.*;

public class ConsoleInput  {
	
	private Scanner scanner = new Scanner(System.in);
	
	public int askInt(String question) {
		System.out.print(question);
		return scanner.nextInt();
	}
	public String askString(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
}
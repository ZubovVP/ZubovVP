package ru.job4j.start;

public class StartUI {
	private Input input;
	private static Tracker tracker = new Tracker();

	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	public static void main (String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input, tracker).init();
	}

	public void init(){
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Select: "));
			menu.select(key);
		}while(!"Yes".equals(this.input.ask("Exit? Yes/No?")));

	}
	}

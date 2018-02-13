package ru.job4j.start;

public class StartUI {
	Tracker tracker = new Tracker();
	private Input input;
	private int[] ranges;

	public StartUI(Input input, Tracker tracker) {

		this.input = input;
		this.tracker = tracker;
	}
	public static void main (String[] args) {
		Tracker tracker = new Tracker();
		Input input = new ValidateInput();
		new StartUI(input, tracker).init();
	}

	public void init(){
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		this.ranges = menu.fillActions();

		UserAction deleteAction = new UserAction() {
			
			public int key() {
				return 6;
			}
			
			public void execute(Input input, Tracker tracker) {
				/*todo
				Сделан для образца в обучающих целях, в программе не работает(Пользоваться "Delete Item")
				 */
			}
			
			public String info() {
				return "6. DELETE";
			}
		};
		menu.addAction(deleteAction);
		do {
			menu.show();
			menu.select(input.ask("Select:", ranges));
		}while(!"Yes".equals(this.input.ask("Exit? Yes/No?")));
	}
	}

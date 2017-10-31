package ru.job4j.tracker;

public class StartUI {

	private static String description;
	private static String name;
	private static long time;

	private static final int addNewItem = 0;
	private static final int showAllItems = 1;
	private static final int editItem = 2;
	private static final int deleteItem = 3;
	private static final int findItemById = 4;
	private static final int findItemByName = 5;
	private static final int exitProgram = 6;

	public static void main (String[] args) {
		ConsoleInput input = new ConsoleInput();
		Tracker tracker = new Tracker();

		while(1 > 0) {
			int number = input.askInt("0. Add new Item" + System.getProperty("line.separator") + "1. Show all items" + System.getProperty("line.separator") + "2. Edit item" + System.getProperty("line.separator") + "3. Delete item" + System.getProperty("line.separator") + "4. Find item by Id" + System.getProperty("line.separator") + "5. Find items by name" + System.getProperty("line.separator") + "6. Exit Program" + System.getProperty("line.separator") + "Please, write number");
			if (number == addNewItem ) {
				name = input.askString("Please, write name");
				description = input.askString("Please, write description");
				time = System.currentTimeMillis();
				tracker.add(new Item(name , description, time));
				System.out.println("Item succsessful added!");
			} else if (number == showAllItems) {
				System.out.println("Name, Description, Id");
				for(int it = 0; it <tracker.getAll().length && tracker.getAll()[it] != null; it++ ){
					System.out.println(tracker.getAll()[it].getName() + ", " + tracker.getAll()[it].getDescription() + ", " + tracker.getAll()[it].getId() + System.getProperty("line.separator"));
				}
			} else if(number == editItem){
				name = input.askString("Please, write new name");
				description = input.askString("Please, write new description");
				time = System.currentTimeMillis();
				tracker.update(new Item(name, description, time));
				System.out.println("Item succsessful edit!");
			} else if(number == deleteItem){
				tracker.delete(input.askString("Please, write Id"));
				System.out.println("Item succsessful delete!");
			} else if(number == findItemById){
				System.out.println(tracker.findById(input.askString("Please, write Id")));
			} else if(number == findItemByName){
				System.out.println(tracker.findById(input.askString("Please, write name")));
			} else if(number == exitProgram){
				break;
			}
		}
	}
}
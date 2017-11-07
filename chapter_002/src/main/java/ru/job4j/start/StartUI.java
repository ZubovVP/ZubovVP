package ru.job4j.start;

import ru.job4j.models.*;

public class StartUI {

	private static final int AddNewItem = 0;
	private static final int ShowAllItems = 1;
	private static final int EditItem = 2;
	private static final int DeleteItem = 3;
	private static final int FindItemById = 4;
	private static final int FindItemByName = 5;
	private static final int ExitProgram = 6;

	public static void main (String[] args) {
		ConsoleInput input = new ConsoleInput();
		Tracker tracker = new Tracker();

		while(1 > 0) {
			int number = input.askInt("0. Add new Item" + System.getProperty("line.separator") + "1. Show all items" + System.getProperty("line.separator") + "2. Edit item" + System.getProperty("line.separator") + "3. Delete item" + System.getProperty("line.separator") + "4. Find item by Id" + System.getProperty("line.separator") + "5. Find items by name" + System.getProperty("line.separator") + "6. Exit Program" + System.getProperty("line.separator") + "Please, write number : ");
			if (number == AddNewItem ) {
					tracker.add(new Item(input.askString("Please, write name :"), input.askString("Please, write description :"), System.currentTimeMillis()));
					System.out.println("Item succsessful added!" + System.getProperty("line.separator"));
			} else if (number == ShowAllItems) {
				System.out.println("Name, Description, Id");
				for(int it = 0; it <tracker.getAll().length && tracker.getAll()[it] != null; it++ ){
					System.out.println(tracker.getAll()[it].getName() + ", " + tracker.getAll()[it].getDescription() + ", " + tracker.getAll()[it].getId() + System.getProperty("line.separator"));
				}
			} else if(number == EditItem){
				tracker.update(new Item(input.askString("Please, write new name :"), input.askString("Please, write new description :"), System.currentTimeMillis()));
				System.out.println("Item succsessful edit!");
			} else if(number == DeleteItem){
				tracker.delete(input.askString("Please, write Id : "));
				System.out.println("Item succsessful delete!");
			} else if(number == FindItemById){
				System.out.println(tracker.findById(input.askString("Please, write Id : ")));
			} else if(number == FindItemByName){
				System.out.println(tracker.findById(input.askString("Please, write name : ")));
			} else if(number == ExitProgram){
				break;
			}
		}
	}
}
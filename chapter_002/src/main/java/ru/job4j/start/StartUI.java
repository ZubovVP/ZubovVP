package ru.job4j.start;

import ru.job4j.models.*;

public class StartUI {
	private static final String ADD_NEW_ITEM = "0";
	private static final String SHOW_ALL_ITEMS = "1";
	private static final String EDIT_ITEM = "2";
	private static final String DELETE_ITEM = "3";
	private static final String FIND_ITEM_BY_ID = "4";
	private static final String FIND_ITEM_BY_NAME = "5";
	private static final String EXIT_PROGRAM = "6";
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
		Item it = new Item();
		String name;
		String description;
		String id;

		while(1>0){
			String number = input.ask(System.getProperty("line.separator") + "0. Add new Item" + System.getProperty("line.separator") + "1. Show all items" + System.getProperty("line.separator") + "2. Edit item" + System.getProperty("line.separator") + "3. Delete item" + System.getProperty("line.separator") + "4. Find item by Id" + System.getProperty("line.separator") + "5. Find items by name" + System.getProperty("line.separator") + "6. Exit Program" + System.getProperty("line.separator")+ "Please, write number : ");
			if(number.equals(ADD_NEW_ITEM)){
				name = input.ask("Please, enter the task's name : ");
				description = input.ask("Please, enter the task's description : ");
				tracker.add(new Item (name, description, System.currentTimeMillis()));
				System.out.println("Item sucsessful added! " );
				continue;
			} else if(number.equals(SHOW_ALL_ITEMS)){
				for(Item item : tracker.getAll()){
					System.out.println(System.getProperty("line.separator") + "Name : " + item.getName() + "; " + System.getProperty("line.separator") + "Descreption : " + item.getDescription() + "; " + System.getProperty("line.separator") + "Id :" + item.getId() + " ;" + System.getProperty("line.separator") + "Create :" + item.getCreate() + "." + System.getProperty("line.separator") );
					continue;
				}
			} else if (number.equals(EDIT_ITEM)){
					id = input.ask("Please, enter the task's id : ");
					name = input.ask("Please, enter new name : ");
					description = input.ask("Please, enter new description : ");
					it = tracker.findById(id);
					it.name = name;
					it.description = description;
				System.out.println("Item edited ! " + System.getProperty("line.separator"));
				continue;
				} else if (number.equals(DELETE_ITEM)){
				id = input.ask("Please, enter the task's id : ");
				tracker.delete(id);
				System.out.println("Item deleted ! " + System.getProperty("line.separator"));
				continue;
			} else if(number.equals(FIND_ITEM_BY_ID)){
				id = input.ask("Please, enter the task's id: ");
				it = tracker.findById(id);
				System.out.println(System.getProperty("line.separator") + "Name : " + it.getName() + System.getProperty("line.separator")  + "Descreption : " + it.getDescription() + "; " + System.getProperty("line.separator") + "Id : " + it.getId() + "; " + System.getProperty("line.separator") + "Create : " + it.getCreate() + ". ");
				continue;
			} else if (number.equals(FIND_ITEM_BY_NAME)){
				name = input.ask("Please, enter the task's name : ");
				it = tracker.findByName(name);
				System.out.println(System.getProperty("line.separator") + "Name : " + it.getName() + System.getProperty("line.separator")  + "Descreption : " + it.getDescription() + "; " + System.getProperty("line.separator") + "Id : " + it.getId() + "; " + System.getProperty("line.separator") + "Create : " + it.getCreate() + ". ");
				continue;
			} else if (number.equals(EXIT_PROGRAM)){
				break;
			} else{
				System.out.println(System.getProperty("line.separator") + "Please write correct number" );
				continue;
			}
		}
		
	}
	

	}

package ru.job4j.start;

import ru.job4j.models.*;

public class StartUI {
	private static String ADD_NEW_ITEM = "0";
	private static String SHOW_ALL_ITEMS = "1";
	private static String EDIT_ITEM = "2";
	private static String DELETE_ITEM = "3";
	private static String FIND_ITEM_BY_ID = "4";
	private static String FIND_ITEM_BY_NAME = "5";
	private static String EXIT_PROGRAM = "6";
	public static void main (String[] args) {
	ConsoleInput input = new ConsoleInput();
		Tracker tracker = new Tracker();
		String name;
		String description;
		String id;
		Item it = new Item();

		while(1>0){
			System.getProperty("line.separator");
			System.out.println("0.Add new item");
			System.out.println("1.Show all items");
			System.out.println("2.Edit item");
			System.out.println("3.Delete item");
			System.out.println("4.Find item by Id");
			System.out.println("5.Find item by name");
			System.out.println("6.Exit program");
			String number = input.ask("Please, write number : ");
			if(number.equals(ADD_NEW_ITEM)){
				name = input.ask("Please, enter the task's name : ");
				description = input.ask("Please, enter the task's description : ");
				tracker.add(new Item (name, description, System.currentTimeMillis()));
				System.out.println("Item sucsessful added! " + System.getProperty("line.separator"));
				continue;
			} else if(number.equals(SHOW_ALL_ITEMS)){
				for(Item item : tracker.getAll()){
					System.out.println("Name : " + item.getName() + "; ");
					System.out.println("Descreption : " + item.getDescription() + "; ");
					System.out.println("Id :" + item.getId() + " ;");
					System.out.println("Create :" + item.getCreate() + "." + System.getProperty("line.separator"));
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
				System.out.println("Name : " + it.getName() + ", ");
				System.out.print("Descreption : " + it.getDescription() + ", ");
				System.out.print("Id : " + it.getId());
				System.out.print("Create : " + it.getCreate() + System.getProperty("line.separator"));
				continue;
			} else if (number.equals(FIND_ITEM_BY_NAME)){
				name = input.ask("Please, enter the task's name : ");
				it = tracker.findByName(name);
				System.out.println("Name : " + it.getName() + ", ");
				System.out.print("Descreption : " + it.getDescription() + ", ");
				System.out.print("Id : " + it.getId());
				System.out.print("Create : " + it.getCreate() + System.getProperty("line.separator"));
				continue;
			} else if (number.equals(EXIT_PROGRAM)){
				break;
			} else{
				System.out.println("Please write currect number" + System.getProperty("line.separator"));
				continue;
			}
		}
		}
	}

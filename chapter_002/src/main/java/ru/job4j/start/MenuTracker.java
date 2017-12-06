package ru.job4j.start;

import ru.job4j.models.Item;

class EditItem implements UserAction {
		public int key(){
			return 2;
		}
		public void execute (Input input, Tracker tracker){
			String id = input.ask("Please, enter the task's id : ");
			String name = input.ask("Please, enter new name : ");
			String description = input.ask("Please, enter new description : ");
			Item it = new Item();
					it = tracker.findById(id);
					it.name = name;
					it.description = description;
				System.out.println("Item edited ! " + System.getProperty("line.separator"));
			
		}
		public String info(){
			return String.format("%s. %S", this.key(), "Edit the new item. ");
		}
	}
	class FindItemById implements UserAction {
		public int key(){
			return 4;
		}
		public void execute (Input input, Tracker tracker){
			String id = input.ask("Please, enter the task's id: ");
			Item it = new Item();
				it = tracker.findById(id);
				System.out.println(System.getProperty("line.separator") + "Name : " + it.getName() + System.getProperty("line.separator")  + "Descreption : " + it.getDescription() + "; " + System.getProperty("line.separator") + "Id : " + it.getId() + "; " + System.getProperty("line.separator") + "Create : " + it.getCreate() + ". ");
		}
		public String info(){
			return String.format("%s. %S", this.key(), "Find item by id. ");
		}
	}


public class MenuTracker {
	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[6];
	private String name;
	private String description;
	private String id;

	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	public void fillActions() {
		this.actions[0] = this.new AddItem();
		this.actions[1] = new MenuTracker.ShowItems();
		this.actions[2] = new EditItem();
		this.actions[3] = this.new DeleteItem();
		this.actions[4] = new FindItemById();
		this.actions[5] = new FindItemByName();
	}

	public void show() {
		for (UserAction action : this.actions) {
			if (action != null)
				System.out.println(action.info());
		}
	}

	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}

	private class AddItem implements UserAction {
		public int key() {
			return 0;
		}

		public void execute(Input input, Tracker tracker) {
			name = input.ask("Please, enter the task's name : ");
			description = input.ask("Please, enter the task's description : ");
			tracker.add(new Item(name, description, System.currentTimeMillis()));

		}

		public String info() {
			return String.format("%s. %S", this.key(), "Add the new item. ");
		}
	}

	private class DeleteItem implements UserAction {
		public int key() {
			return 3;
		}

		public void execute(Input input, Tracker tracker) {
			id = input.ask("Please, enter the task's id : ");
			tracker.delete(id);
			System.out.println("Item deleted ! " + System.getProperty("line.separator"));
		}

		public String info() {
			return String.format("%s. %S", this.key(), "Delete item. ");
		}
	}

	private static class ShowItems implements UserAction {
		public int key() {
			return 1;
		}

		public void execute(Input input, Tracker tracker) {
			for (Item item : tracker.getAll()) {
				System.out.println(System.getProperty("line.separator") + "Name : " + item.getName() + "; " + System.getProperty("line.separator") + "Descreption : " + item.getDescription() + "; " + System.getProperty("line.separator") + "Id :" + item.getId() + " ;" + System.getProperty("line.separator") + "Create :" + item.getCreate() + "." + System.getProperty("line.separator"));
			}

		}

		public String info() {
			return String.format("%s. %S", this.key(), "Show all items. ");
		}
	}

	private static class FindItemByName implements UserAction {
		public int key() {
			return 5;
		}

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the task's name : ");
			Item it = new Item();
			it = tracker.findByName(name);
			System.out.println(System.getProperty("line.separator") + "Name : " + it.getName() + System.getProperty("line.separator") + "Descreption : " + it.getDescription() + "; " + System.getProperty("line.separator") + "Id : " + it.getId() + "; " + System.getProperty("line.separator") + "Create : " + it.getCreate() + ". ");
		}

		public String info() {
			return String.format("%s. %S", this.key(), "Find item by name. ");
		}
	}
}
// как java определяет к какому действию мы обращаемся если мы просим перейти в метод execute

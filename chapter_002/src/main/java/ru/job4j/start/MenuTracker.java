package ru.job4j.start;

import ru.job4j.models.Item;

class EditItem extends BaseAction {
	public EditItem(int key, String name) {
		super(key, name);
	}

	@Override
	public void execute(Input input, Tracker tracker) {
		String id = input.ask("Please, enter the task's id : ");
		String name = input.ask("Please, enter new name : ");
		String description = input.ask("Please, enter new description : ");
		tracker.findById(id).setName(name);
		tracker.findById(id).setDescription(description);
		System.out.println("Item edited ! " + System.getProperty("line.separator"));
	}
}

	class FindItemById extends BaseAction {
		public FindItemById(int key, String name) {
			super(key, name);
		}

		@Override
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please, enter the task's id: ");
			System.out.println(System.getProperty("line.separator") + "Name : " + tracker.findById(id).getName() + System.getProperty("line.separator") + "Descreption : " + tracker.findById(id).getDescription() + "; " + System.getProperty("line.separator") + "Id : " + tracker.findById(id).getId() + "; " + System.getProperty("line.separator") + "Create : " + tracker.findById(id).getCreate() + ". ");
		}
	}

public class MenuTracker {
	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[7];
	private int position = 0;
	private String name;
	private String description;
	private String id;
	private int[] ranges;
	private int numberOfActions = 0;

	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	public int[] fillActions() {
		this.actions[position++] = this.new AddItem(0, "AddItem" );
		this.actions[position++] = new MenuTracker.ShowItems(1, "ShowItems");
		this.actions[position++] = new EditItem(2, "EditItem");
		this.actions[position++] = this.new DeleteItem(3, "DeleteItem");
		this.actions[position++] = new FindItemById(4,"FindItemById" );
		this.actions[position++] = new FindItemByName(5,"FindItemByName" );
		this.ranges = new int[actions.length];
		while (numberOfActions < actions.length) {
			this.ranges[numberOfActions] = numberOfActions;
			numberOfActions++;
		}
		return ranges;
	}

	public void addAction(UserAction action) {
		this.actions[position++] = action;
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

	private class AddItem extends BaseAction {
		public AddItem(int key, String name) {
			super(key, name);
		}

		@Override
		public void execute(Input input, Tracker tracker) {
			name = input.ask("Please, enter the task's name : ");
			description = input.ask("Please, enter the task's description : ");
			tracker.add(new Item(name, description, System.currentTimeMillis()));
		}
	}

	private class DeleteItem extends BaseAction {
		public DeleteItem(int key, String name) {
			super(key, name);
		}

		@Override
		public void execute(Input input, Tracker tracker) {
			id = input.ask("Please, enter the task's id : ");
			tracker.delete(id);
			System.out.println("Item deleted ! " + System.getProperty("line.separator"));
		}
	}

		private static class ShowItems extends BaseAction {
			public ShowItems(int key, String name) {
				super(key, name);
			}

			@Override
			public void execute(Input input, Tracker tracker) {
				for (Item item : tracker.getAll()) {
					System.out.println(System.getProperty("line.separator") + "Name : " + item.getName() + "; " + System.getProperty("line.separator") + "Descreption : " + item.getDescription() + "; " + System.getProperty("line.separator") + "Id :" + item.getId() + " ;" + System.getProperty("line.separator") + "Create :" + item.getCreate() + "." + System.getProperty("line.separator"));
				}
			}
			}

			private static class FindItemByName extends BaseAction {
				public FindItemByName(int key, String name) {
					super(key, name);
				}

				@Override
				public void execute(Input input, Tracker tracker) {
					String name = input.ask("Please, enter the task's name : ");
					System.out.println(System.getProperty("line.separator") + "Name : " + tracker.findByName(name).getName() + System.getProperty("line.separator") + "Descreption : " + tracker.findByName(name).getDescription() + "; " + System.getProperty("line.separator") + "Id : " + tracker.findByName(name).getId() + "; " + System.getProperty("line.separator") + "Create : " + tracker.findByName(name).getCreate() + ". ");
				}
			}
}


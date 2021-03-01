package ru.job4j.start;

import ru.job4j.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class  DeleteItem extends BaseAction {

    public DeleteItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please, write the id :");
        tracker.delete(Integer.parseInt(id));
        System.out.println("Item deleted !");
    }
}

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
class  EditItem extends BaseAction {

    public EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please, write the id :");
        String name = input.ask("Please, write the new task's name :");
        String description = input.ask("Please, write the new task's description");
        Item result = tracker.findById(Integer.parseInt(id));
        result.setName(name);
        result.setDescription(description);
        tracker.replace(result);
        System.out.println("Item edited !");
    }
}

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public List<UserAction> fillActions() {
        this.actions.add(this.new AddItem(0, "Add the new Item. "));
        this.actions.add(new MenuTracker.ShowItems(1, "Show all Items. "));
        this.actions.add(new EditItem(2, "Edit item. "));
        this.actions.add(new DeleteItem(3, "Delete item. "));
        this.actions.add(this.new FindById(4, "Find by id. "));
        this.actions.add(new MenuTracker.FindItemByName(5, "Find item by name. "));
        return this.actions;
    }
    public void addAction(UserAction action) {
        this.actions.add(action);
    }

    public void show() {
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
    }

    /**
     * @author Vitaly Zubov (zubovvp@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, write the task's name :");
            String description = input.ask("Please, write the task's description");
            tracker.add(new Item(name, description, System.currentTimeMillis()));
            System.out.println("Item added!");
        }
    }

    /**
     * @author Vitaly Zubov (zubovvp@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private class FindById extends BaseAction {

        public FindById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, write the id :");
            Item it = tracker.findById(Integer.parseInt(id));
            System.out.print(System.getProperty("line.separator") + " Name : " + it.getName() + ";"
                    + System.getProperty("line.separator") + "Description :" + it.getDescription() + ";"
                    + System.getProperty("line.separator") + "Id :" + it.getId() + ";"
                    + System.getProperty("line.separator") + "Create :" + it.getCreateOfDate() + ";"
                    + System.getProperty("line.separator"));
        }
    }

    /**
     * @author Vitaly Zubov (zubovvp@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private static class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.findAllByReact(e -> System.out.print(System.getProperty("line.separator") + " Name : " + e.getName() + ";"
                    + System.getProperty("line.separator") + "Description :" + e.getDescription() + ";"
                    + System.getProperty("line.separator") + "Id :" + e.getId() + ";"
                    + System.getProperty("line.separator") + "Create :" + e.getCreateOfDate() + ";"
                    + System.getProperty("line.separator")));
        }
    }

    /**
     * @author Vitaly Zubov (zubovvp@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    private static class FindItemByName extends BaseAction {

        public FindItemByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, write name :");
            Item it = tracker.findByName(name).get(0);
            System.out.print(System.getProperty("line.separator") + " Name : " + it.getName() + ";"
                    + System.getProperty("line.separator") + "Description :" + it.getDescription() + ";"
                    + System.getProperty("line.separator") + "Id :" + it.getId() + ";"
                    + System.getProperty("line.separator") + "Create :" + it.getCreateOfDate() + ";"
                    + System.getProperty("line.separator"));
        }
    }
}

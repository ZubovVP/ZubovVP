package ru.job4j.start;

import ru.job4j.models.*;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private Input input;
    private Tracker tracker;
    private  int[] ranges;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        Tracker tracker = new Tracker();
        ranges = menu.fillActions();
        UserAction deleteAction = new UserAction() {
            @Override
            public int key() {
                return 6;
            }

            @Override
            public void execute(Input input, Tracker tracker) {
                //todo
            }

            @Override
            public String info() {
                return "Delete";
            }
        };
        menu.addAction(deleteAction);
        do {
            menu.show();
            menu.select(input.ask("Select", ranges));
        } while (!"Yes".equals(this.input.ask("Exit? Yes/No?")));
    }

    public static void main(String[] args) {
       new StartUI(
               new ValidateInput(
                       new ConsoleInput()
               ),
               new Tracker()
               ).init();
    }
}


package ru.job4j.start;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
@Component
public class StartUI {
    private Input input;
    private Tracker tracker;

    public StartUI(ValidateInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        List<UserAction> ranges = menu.fillActions();
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
        Context context = new Context();
        context.reg(ConsoleInput.class);
        context.reg(ValidateInput.class);
        context.reg(Tracker.class);
        context.reg(StartUI.class);
        StartUI ui = context.get(StartUI.class);
        ui.init();
    }
}


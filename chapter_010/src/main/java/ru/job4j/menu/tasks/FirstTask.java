package ru.job4j.menu.tasks;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.12.2019.
 */
public class FirstTask extends AbstractTask {
    public FirstTask(String name, List<AbstractTask> parents) {
        super(name, parents);
    }

    public FirstTask(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println(String.format("---%s", this.name));
    }
}

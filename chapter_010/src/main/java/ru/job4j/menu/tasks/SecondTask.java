package ru.job4j.menu.tasks;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.12.2019.
 */
public class SecondTask extends AbstractTask {
    public SecondTask(String name, List<AbstractTask> parents) {
        super(name, parents);
    }

    public SecondTask(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println(String.format("-----%s", this.name));
    }
}

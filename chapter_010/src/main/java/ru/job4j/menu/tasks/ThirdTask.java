package ru.job4j.menu.tasks;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.12.2019.
 */
public class ThirdTask extends AbstractTask {

    public ThirdTask(String name, List<AbstractTask> parents) {
        super(name, parents);
    }

    public ThirdTask(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println(String.format("-------%s", this.name));
    }
}

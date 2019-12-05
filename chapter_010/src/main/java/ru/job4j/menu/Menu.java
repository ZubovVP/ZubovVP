package ru.job4j.menu;

import ru.job4j.menu.tasks.AbstractTask;
import ru.job4j.menu.tasks.FirstTask;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.12.2019.
 */
public class Menu {
    private AbstractTask root;

    /**
     * Constructor
     *
     * @param root - main task.
     */
    public Menu(AbstractTask root) {
        this.root = root;
    }

    /**
     * Constructor.
     * Create first level task with is empty list of children.
     *
     * @param name - name task.
     */
    public Menu(String name) {
        this.root = new FirstTask(name);
    }

    /**
     * Print all tasks.
     */
    public void printAll() {
        this.root.printAll();
    }


    /**
     * Print this task.
     */
    public void print() {
        this.root.print();
    }
}

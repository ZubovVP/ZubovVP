package ru.job4j.menu.tasks;

import ru.job4j.menu.Printer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.12.2019.
 */
public abstract class AbstractTask implements Printer {
    protected String name;
    protected List<AbstractTask> children;

    /**
     * Constructor.
     *
     * @param name - name of task.
     * @param children - list of children.
     */
    public AbstractTask(String name, List<AbstractTask> children) {
        this.name = name;
        this.children = children;
    }

    /**
     * Constructor.
     * Create first level task with is empty list of children.
     *
     * @param name - name of task.
     */
    public AbstractTask(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    /**
     * Add task in list of cheldren.
     *
     * @param task - task.
     * @return - result.
     */
    public boolean addChildren(AbstractTask task) {
        return this.children.add(task);
    }

    /**
     * Get name.
     *
     * @return - name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Print all task.
     */
    public void printAll() {
        this.print();
        if (!this.children.isEmpty()) {
            for (AbstractTask object : this.children) {
                object.printAll();
            }
        }
    }
}

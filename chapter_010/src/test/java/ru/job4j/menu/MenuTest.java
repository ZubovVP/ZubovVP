package ru.job4j.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.menu.tasks.AbstractTask;
import ru.job4j.menu.tasks.FirstTask;
import ru.job4j.menu.tasks.SecondTask;
import ru.job4j.menu.tasks.ThirdTask;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 05.12.2019.
 */
public class MenuTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));

    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }


    @Test
    public void testPrintWithFirstTask() {
        AbstractTask task = new FirstTask("Task1");
        Menu menu = new Menu(task);
        menu.printAll();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("---")
                .append(task.getName())
                .append(System.lineSeparator()).toString()));
    }

    @Test
    public void testPrintWithSecondTask() {
        AbstractTask task = new SecondTask("Task2");
        Menu menu = new Menu(task);
        menu.printAll();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("-----")
                .append(task.getName())
                .append(System.lineSeparator()).toString()));
    }

    @Test
    public void testPrintWithThirdTask() {
        AbstractTask task = new ThirdTask("Task3");
        Menu menu = new Menu(task);
        menu.printAll();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("-------")
                .append(task.getName())
                .append(System.lineSeparator()).toString()));
    }


    @Test
    public void testPrintWithAllTask() {
        AbstractTask task3 = new ThirdTask("Task3");
        AbstractTask task2 = new SecondTask("Task2");
        AbstractTask task1 = new FirstTask("Task1");
        task2.addChildren(task3);
        task1.addChildren(task2);
        Menu menu = new Menu(task1);
        menu.printAll();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("---")
                .append(task1.getName())
                .append(System.lineSeparator())
                .append("-----")
                .append(task2.getName())
                .append(System.lineSeparator())
                .append("-------")
                .append(task3.getName())
                .append(System.lineSeparator()).toString()));
    }

    @Test
    public void testPrintWithAllTask2() {
        AbstractTask task1 = new FirstTask("Task1");
        task1.addChildren(new SecondTask("Task1.1"));
        task1.addChildren(new SecondTask("Task1.2"));
        task1.addChildren(new SecondTask("Task1.3"));
        Menu menu = new Menu(task1);
        menu.printAll();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("---")
                .append(task1.getName())
                .append(System.lineSeparator())
                .append("-----")
                .append("Task1.1")
                .append(System.lineSeparator())
                .append("-----")
                .append("Task1.2")
                .append(System.lineSeparator())
                .append("-----")
                .append("Task1.3")
                .append(System.lineSeparator()).toString()));
    }

}
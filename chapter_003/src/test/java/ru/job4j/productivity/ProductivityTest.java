package ru.job4j.productivity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class ProductivityTest {

    @Test
    public void addTestArrayList() throws Exception {
        Productivity test = new Productivity();
        ArrayList<String> list = new ArrayList<>();
        long result = test.add(list, 10000);
        System.out.printf("%s%d%n", "Add ArrayList - ", result);
    }

    @Test
    public void deleteTestArrayList() throws Exception {
        Productivity test = new Productivity();
        ArrayList<String> list = new ArrayList<>();
        long result = test.delete(list, 10000);
        System.out.printf("%s%d%n", "Remove ArrayList - ", result);
    }

    @Test
    public void addTestLinkedList() throws Exception {
        Productivity test = new Productivity();
        LinkedList<String> list = new LinkedList<>();
        long result = test.add(list, 10000);
        System.out.printf("%s%d%n", "Add LinkedList - ", result);
    }

    @Test
    public void deleteTestLinkedList() throws Exception {
        Productivity test = new Productivity();
        LinkedList<String> list = new LinkedList<>();
        long result = test.delete(list, 10000);
        System.out.printf("%s%d%n", "Remove LinkedList - ", result);
    }

    @Test
    public void addTestTreeSet() throws Exception {
        Productivity test = new Productivity();
        TreeSet<String> list = new TreeSet<>();
        long result = test.add(list, 10000);
        System.out.printf("%s%d%n", "Add TreeSet - ", result);
    }

    @Test
    public void deleteTestTreeSet() throws Exception {
        Productivity test = new Productivity();
        TreeSet<String> list = new TreeSet<>();
        long result = test.delete(list, 10000);
        System.out.printf("%s%d%n", "Remove TreeSet - ", result);
    }

}
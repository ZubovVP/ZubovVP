package ru.job4j.productivityCollections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class ProductivityCollectionsTest {
    @Test
    public void addTestArrayList() throws Exception {
        ProductivityCollections test = new ProductivityCollections();
        ArrayList<String> list = new ArrayList<>();
        long result = test.add(list, 10000);
        System.out.printf("%s%d%n", "Add ArrayList - " , result);
    }

    @Test
    public void deleteTestArrayList() throws Exception {
        ProductivityCollections test = new ProductivityCollections();
        ArrayList<String> list = new ArrayList<>();
        long result = test.delete(list, 10000);
        System.out.printf("%s%d%n", "Remove ArrayList - " , result);
    }
    @Test
    public void addTestLinkedList() throws Exception {
        ProductivityCollections test = new ProductivityCollections();
        LinkedList<String> list = new LinkedList<>();
        long result = test.add(list, 10000);
        System.out.printf("%s%d%n", "Add LinkedList - " , result);
    }
    @Test
    public void deleteTestLinkedList() throws Exception {
        ProductivityCollections test = new ProductivityCollections();
        LinkedList<String> list = new LinkedList<>();
        long result = test.delete(list, 10000);
        System.out.printf("%s%d%n", "Remove LinkedList - " , result);
    }
    @Test
    public void addTestTreeSet() throws Exception {
        ProductivityCollections test = new ProductivityCollections();
        TreeSet<String> list = new TreeSet<>();
        long result = test.add(list, 10000);
        System.out.printf("%s%d%n", "Add TreeSet - " , result);
    }
    @Test
    public void deleteTestTreeSet() throws Exception {
        ProductivityCollections test = new ProductivityCollections();
        TreeSet<String> list = new TreeSet<>();
        long result = test.delete(list, 10000);
        System.out.printf("%s%d%n", "Remove TreeSet - " , result);
    }

}
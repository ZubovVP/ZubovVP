package ru.job4j.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class DepartmentTest {
    private List<String> test;
    private Department department = new Department();

    @Before
    public void createDepartment() {
        this.department.addSubdivision("K1\\SK1");
        this.department.addSubdivision("K1\\SK2");
        this.department.addSubdivision("K1\\SK1\\SSK1");
        this.department.addSubdivision("K1\\SK1\\SSK2");
        this.department.addSubdivision("K2");
        this.department.addSubdivision("K2\\SK1\\SSK1");
        this.department.addSubdivision("K2\\SK1\\SSK2");
        System.out.println("Было после добавления : " +  this.department.getDirectory());
    }

    @Test
    public void addDepartament() throws Exception {
        this.department.addSubdivision("K3\\SK1\\SSK2");
        assertTrue(this.department.getDirectory().contains("K3"));
        assertTrue(this.department.getDirectory().contains("K3\\SK1"));
        assertTrue(this.department.getDirectory().contains("K3\\SK1\\SSK2"));
    }

    @Test
    public void sortAscendeng() throws Exception {
        this.department.sortAscendeng();
        this.test = department.getDirectory();
        System.out.println("Стало после сортировки :" + this.test);
        assertThat(this.test.get(0), is("K1"));
        assertThat(this.test.get(1), is("K1\\SK1"));
        assertThat(this.test.get(2), is("K1\\SK1\\SSK1"));
        assertThat(this.test.get(this.test.size() - 1), is("K2\\SK1\\SSK2"));

    }

    @Test
    public void sortDecreasing() throws Exception {
        this.department.sortDecreasing();
        this.test = department.getDirectory();
        System.out.println("Стало после сортировки :" + this.test);
        assertThat(this.test.get(0), is("K2"));
        assertThat(this.test.get(1), is("K2\\SK1"));
        assertThat(this.test.get(2), is("K2\\SK1\\SSK2"));
        assertThat(this.test.get(this.test.size() - 1), is("K1\\SK1\\SSK1"));
    }
}
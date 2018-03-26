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
    private List<String> test = new ArrayList<>();
    @Before
    public void createDepartment() {
        test.addAll(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
    }

    @Test
    public void getDirectory() throws Exception {
        Department department = new Department(test);
        assertThat(department.getDirectory(), is(test));
    }

    @Test
    public void addDepartament() throws Exception {
        Department department = new Department(test);
        department.addSubdivision("K3\\SK1\\SSK2");
        assertTrue(department.getDirectory().contains("K3"));
        assertTrue(department.getDirectory().contains("K3\\SK1"));
        assertTrue(department.getDirectory().contains("K3\\SK1\\SSK2"));
    }

    @Test
    public void deleteDepartament() throws Exception {
        Department department = new Department(test);
        department.deleteSubdivision("K1\\SK1");
        assertFalse(department.getDirectory().contains("K1\\SK1"));
        assertFalse(department.getDirectory().contains("K1\\SK1\\SSK1"));
        assertFalse(department.getDirectory().contains("K1\\SK1\\SSK2"));
    }

    @Test
    public void sortAscendeng() throws Exception {
        Department department = new Department(test);
        department.sortAscendeng();
        List<String> sortedTest = department.getDirectory();
        System.out.println(sortedTest);
        assertThat(sortedTest.get(0), is("K1\\SK1"));
        assertThat(sortedTest.get(1), is("K1\\SK1\\SSK1"));
        assertThat(sortedTest.get(sortedTest.size() - 1), is("K2\\SK1\\SSK2"));

    }

    @Test
    public void sortDecreasing() throws Exception {
        Department department = new Department(test);
        department.sortDecreasing();
        List<String> sortedTest = department.getDirectory();
        System.out.println(sortedTest);
        assertThat(sortedTest.get(0), is("K2"));
        assertThat(sortedTest.get(1), is("K2\\SK1\\SSK2"));
        assertThat(sortedTest.get(sortedTest.size() - 1), is("K1\\SK1"));
    }
}
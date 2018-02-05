package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConvertTest {
    @Test
    public void toList() throws Exception {
        Convert convertList = new Convert();
        //create the array
        int a = 3;
        int b = 3;
        int[][] intTest = new int[a][b];
        int count = 0;

        for (int x = 0; x < a  &&  x < intTest[x].length; x++) {
            for (int[] anIntTest : intTest) {
                count++;
            }
        }
        List<Integer> list;
        list = convertList.toList(intTest);
        assertTrue(list.size() == count);
    }

    @Test
    public void toArray() throws Exception {
        Convert convertList = new Convert();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        int rows = 4;
        int[][] result;
        result = convertList.toArray(list, rows);
        int[][] expected =  {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}};
        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[x].length; y++) {
                assertThat(result[x][y], is(expected[x][y]));
            }
        }
    }


}
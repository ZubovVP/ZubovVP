package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.01.2021.
 */
public class SortIntger {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = Arrays.asList(1, -1, -10, 2, -6, 0);
        List<Integer> result = listOfNumbers.stream()
                .filter(Integer -> Integer >= 0)
                .collect(Collectors.toList());
        System.out.println(result.toString());
    }
}

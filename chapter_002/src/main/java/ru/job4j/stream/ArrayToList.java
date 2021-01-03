package ru.job4j.stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.01.2021.
 */
public class ArrayToList {
    public static void main(String[] args) {
        Integer[][] arrayNumbers = new Integer[][]{{1, 2}, {3, 4}, {5, 6}};
        List<Integer> listNumbers = Stream.of(arrayNumbers)
                .flatMap(Arrays::stream).collect(Collectors.toList());
    }
}

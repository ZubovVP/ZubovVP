package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.01.2021.
 */
public class CalcFunc {


    public static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int x = start; x < end; x++) {
            result.add(func.apply((double) x));
        }
        return result;
    }


}

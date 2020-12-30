package ru.job4j.lambda;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.12.2020.
 */
public class FI {
    public static void main(String[] args) {
        Attachment[] atts = {
                new Attachment("image 1", 20),
                new Attachment("image 3", 120),
                new Attachment("image 2", 23)
        };
        Comparator<Attachment> comparator = new Comparator<Attachment>() {
            @Override
            public int compare(Attachment left, Attachment right) {
                return left.getSize() - right.getSize();
            }
        };
        Arrays.sort(atts, comparator);

        String[] strings = {
                "1", "123", "12"
        };
        Comparator<String> cmpText = (left, right) -> left.compareTo(right);
        Arrays.sort(strings, cmpText);
        System.out.println(Arrays.toString(strings));


        Comparator<String> cmpDescSize = (left, right) -> {
            System.out.println("ReverseOrder compare - " + left + " : " + right);
            return right.length() - left.length();
        };
        Arrays.sort(strings, cmpDescSize);
        System.out.println(Arrays.toString(strings));
    }
}

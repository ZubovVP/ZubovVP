package ru.job4j.stream;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 06.01.2021.
 */
public class StudentLevel {
    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .filter(st -> st != null)
                .sorted((left, right) -> right.getScore() - left.getScore())
                .takeWhile(st -> st.getScore() > bound)
                .collect(Collectors.toList());
    }
}

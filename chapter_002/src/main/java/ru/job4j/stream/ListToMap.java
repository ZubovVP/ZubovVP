package ru.job4j.stream;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.01.2021.
 */
public class ListToMap {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student(10, "Student1"),
                new Student(20, "Student2"),
                new Student(30, "Student3"));
        Map<String, Student> mapStudens = students.stream()
                .distinct()
                .collect(
                        Collectors.toMap(
                                Student::getSurname,
                                Student -> Student));
        System.out.println("aaaa");
    }
}

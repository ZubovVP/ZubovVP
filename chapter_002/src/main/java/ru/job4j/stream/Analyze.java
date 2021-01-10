package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.01.2021.
 */
public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(e -> e.getSubjects().stream())
                .mapToInt(e -> e.getScore())
                .average()
                .orElse(0D);
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map(e -> new Tuple(e.getName(), e.getSubjects()
                        .stream().mapToInt(a -> a.getScore())
                        .average()
                        .orElse(0D)))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .flatMap(e -> e.getSubjects().stream())
                .collect(Collectors.groupingBy(e -> e.getName(), Collectors.averagingDouble(e -> e.getScore())))
                .entrySet().stream()
                .map(e -> new Tuple(e.getKey(), e.getValue())).collect(Collectors.toList());

    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map(e -> new Tuple(e.getName(), e.getSubjects()
                        .stream().mapToInt(a -> a.getScore())
                        .sum()
                ))
                .max((o1, o2) -> (int) (o1.getScore() - o2.getScore()))
                .orElse(new Tuple(null, 0));
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .flatMap(e -> e.getSubjects().stream())
                .collect(Collectors.groupingBy(e -> e.getName(), Collectors.summingDouble(e -> e.getScore())))
                .entrySet().stream()
                .map(e -> new Tuple(e.getKey(), e.getValue()))
                .max(new Comparator<Tuple>() {
                    @Override
                    public int compare(Tuple o1, Tuple o2) {
                        return (int) (o1.getScore() - o2.getScore());
                    }
                }).orElse(null);
    }
}

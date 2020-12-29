package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.12.2020.
 */
public class SearchAtt {

    public static List<Attachment> filterSize(List<Attachment> list) {
        BiFunction<List<Attachment>, Attachment,  List<Attachment>> func = new BiFunction<List<Attachment>, Attachment, List<Attachment>>() {
            @Override
            public List<Attachment> apply(List<Attachment> attachments, Attachment att) {
                    if (att.getSize() > 100) {
                        attachments.add(att);
                    }
                return attachments;
            }
        };
        return filter(func, list);
    }

    public static List<Attachment> filterName(List<Attachment> list) {
        BiFunction<List<Attachment>, Attachment,  List<Attachment>> func = new BiFunction<List<Attachment>, Attachment, List<Attachment>>() {
            @Override
            public List<Attachment> apply(List<Attachment> attachments, Attachment att) {
                if (att.getName().contains("bug")) {
                    attachments.add(att);
                }
                return attachments;
            }
        };
        return filter(func, list);
    }

    private static List<Attachment> filter(BiFunction<List<Attachment>, Attachment,  List<Attachment>> func, List<Attachment> list) {
        List<Attachment> rsl = new ArrayList<>();
        for(Attachment attachment : list){
            rsl = func.apply(rsl, attachment);
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<Attachment> test = Arrays.asList(new Attachment("", 101), new Attachment("", 102), new Attachment("bug", 99));
        System.out.println(SearchAtt.filterName(test).toString());
        System.out.println(SearchAtt.filterSize(test).toString());

    }
}

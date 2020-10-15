package ru.job4j.start;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.10.2020.
 */
public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.start");
        context.refresh();
        StartUI ui = context.getBean(StartUI.class);
        ui.init();
    }
}

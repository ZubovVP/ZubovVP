package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class UserHashTest {
    private final Calendar calendar = new GregorianCalendar(1992, 03, 04);
    private final String name = "Duke";
    private final int age = 2;

    @Test
    public void testUser() {
        Map<User, Object> users = new HashMap<>();
        UserHash userOne = new UserHash(name, age, calendar);
        UserHash userTwo = new UserHash(name, age, calendar);
        users.put(userOne, "UserOne");
        users.put(userTwo, "UserTwo");
        System.out.println(users);
        System.out.println("HashCode 1 - " + userOne.hashCode());
        System.out.println("HashCode 2 - " + userTwo.hashCode());
    }
}
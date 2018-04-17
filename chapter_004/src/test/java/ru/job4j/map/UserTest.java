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
public class UserTest {
    private final Calendar calendar = new GregorianCalendar(1992, 03, 04);
    private final String name = "Duke";
    private final int age = 2;
    private Map<User, Object> users = new HashMap<>();

    @Test
    public void testUser(){
        User userOne = new User(name, age, calendar);
        User userTwo = new User(name, age, calendar);
        this.users.put(userOne, "UserOne");
        this.users.put(userTwo, "UserTwo");
        System.out.println(this.users);
        System.out.println("HashCode 1 - " + userOne.hashCode() );
        System.out.println("HashCode 1 - " + userTwo.hashCode() );
    }
}

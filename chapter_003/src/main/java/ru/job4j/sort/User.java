package ru.job4j.sort;


import java.util.Comparator;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public User(int age, String name) {
        this.age = age;

        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (age != user.age) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(User o) {
       return Integer.compare(this.age, o.age);
    }

    public static Comparator<User> nameComparator = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return Integer.compare(o1.name.length(), o2.name.length());
        }
    };
    public static Comparator<User> allFieldsComparator = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            final int result = o1.name.compareTo(o2.name);
            return result != 0 ? result : Integer.compare(o1.age, o2.age);
        }
    };
}

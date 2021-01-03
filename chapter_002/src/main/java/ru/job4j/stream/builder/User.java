package ru.job4j.stream.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static ru.job4j.stream.builder.Sex.Male;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.01.2021.
 */
public class User {
    private String name;
    private String surname;
    private int age;
    private String password;
    private String city;
    private String address;
    private Sex sex;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(password, user.password) && Objects.equals(city, user.city) && Objects.equals(address, user.address) && sex == user.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, password, city, address, sex);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + ", age=" + age + ", password='" + password + '\'' + ", city='" + city + '\'' + ", address='" + address + '\'' + ", sex=" + sex + '}';
    }

    static class Builder {
        private String name;
        private String surname;
        private int age;
        private String password;
        private String city;
        private String address;
        private Sex sex;

        Builder buildName(String name) {
            this.name = name;
            return this;
        }

        Builder buildSurname(String surname) {
            this.surname = surname;
            return this;
        }

        Builder buildAge(int age) {
            this.age = age;
            return this;
        }

        Builder buildPassword(String password) {
            this.password = password;
            return this;
        }

        Builder buildCity(String city) {
            this.city = city;
            return this;
        }

        Builder buildAddress(String address) {
            this.address = address;
            return this;
        }

        Builder buildSex(Sex sex) {
            this.sex = sex;
            return this;
        }

        User build() {
            User user = new User();
            user.name = name;
            user.surname = surname;
            user.age = age;
            user.password = password;
            user.city = city;
            user.address = address;
            user.sex = sex;
            return user;
        }
    }

    public static void main(String[] args) {
        User user = new Builder().buildName("name")
                .buildSurname("surname")
                .buildAge(32)
                .buildPassword("password")
                .buildCity("city")
                .buildAddress("address")
                .buildSex(Male)
                .build();
        System.out.println(user);
    }
}

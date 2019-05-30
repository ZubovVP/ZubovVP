package ru.job4j.models;

import java.util.Objects;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.04.2019.
 */
public class Person {
    private String name;
    private String surname;
    private String id;
    private String sex;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person(String name, String surname, String id, String sex, String description) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.sex = sex;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(sex, person.sex) && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id, sex, description);
    }
}

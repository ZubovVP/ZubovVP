package ru.job4j.search;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.12.2020.
 */
public class PhoneDictionaryTest {
    private PhoneDictionary phones = new PhoneDictionary();

    @Before
    public void start() {
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Vitaly", "Zubov", "534", "Moscow"));
    }

    @Test
    public void whenFindByName() {
        ArrayList<Person> persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindFindByKeyForName() {
        ArrayList<Person> persons = phones.find("Vi");
        assertThat(persons.size(), is(1));
        assertThat(persons.get(0).getSurname(), is("Zubov"));
    }

    @Test
    public void whenFindFindByKeyForPhone() {
        ArrayList<Person> persons = phones.find("534");
        assertThat(persons.size(), is(2));
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
        assertThat(persons.get(1).getSurname(), is("Zubov"));
    }

    @Test
    public void whenFindFindByKeyForAdress() {
        ArrayList<Person> persons = phones.find("nsk");
        assertThat(persons.size(), is(1));
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
    }

}
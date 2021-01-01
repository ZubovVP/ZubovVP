package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 01.01.2021.
 */
public class PhoneDictionaryTest {
        @Test
        public void whenFindByName() {
            PhoneDictionary phones = new PhoneDictionary();
            phones.add(
                    new Person("Petr", "Arsentev", "534872", "Bryansk")
            );
            phones.add(
                    new Person("Vitaly", "Zubov", "534", "Moscow")
            );
            ArrayList<Person> persons = phones.find("Petr");
            assertThat(persons.size(), is(1));
            assertThat(persons.get(0).getSurname(), is("Arsentev"));

        }
    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Vitaly", "Zubov", "534", "Moscow")
        );
        ArrayList<Person> persons = phones.find("534");
        assertThat(persons.size(), is(2));
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
        assertThat(persons.get(1).getSurname(), is("Zubov"));

    }
}
package ru.job4j.storage;

import ru.job4j.models.Person;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 30.05.2019.
 */
public class MyStorage {
    private ConcurrentHashMap<Integer, Person> storage = new ConcurrentHashMap<>();
    private static MyStorage ourInstance = new MyStorage();
    private AtomicInteger id = new AtomicInteger();


    public static MyStorage getInstance() {
        return ourInstance;
    }

    private MyStorage() {
    }

    public boolean addPerson(Person person) {
        storage.put(id.getAndIncrement(), person);
        return true;
    }

    public ArrayList<Person> getAllPersons() {
        return new ArrayList<>(storage.values());
    }

    public boolean deletePersons(Person person) {
        boolean result = false;
        for (Map.Entry<Integer, Person> entry : storage.entrySet()) {
            Person value = entry.getValue();
            Integer key = entry.getKey();
            if (value.equals(person)) {
                storage.remove(key, value);
                result = true;
            }
        }
        return result;
    }
}

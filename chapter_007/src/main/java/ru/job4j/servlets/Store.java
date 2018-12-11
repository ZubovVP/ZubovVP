package ru.job4j.servlets;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 04.12.2018
 */
public interface Store {
    boolean add(User user);
    boolean update(User user);
    boolean delete(int id);
    List<User> findAll();
    User findById(int id);
}

package ru.job4j.sort;

import java.util.*;

/**
 *  @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 13.02.2018
 */
public class SetUser {
    /**
     * Sort collection
     * @param list - the collection
     * @return Set<User> - the collection.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new HashSet<>();
        Collections.sort(list);
        result.addAll(list);
        return result;
    }

    /**
     * Sort collection by name
     * @param list - the colletion
     * @return List<User> - the collection.
     */
    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, User.nameComparator);
        return list;
    }

    /**
     * Sort collection by name and age
     * @param list - the collection
     * @return List<User> - the collection.
     */
    public List<User> sortByAllField(List<User> list) {
        Collections.sort(list, User.allFieldsComparator);
        return list;
    }
}

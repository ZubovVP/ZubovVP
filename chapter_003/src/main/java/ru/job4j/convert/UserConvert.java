package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 06.02.2018
 */
 class UserConvert {

    /**
     * Convert a collection(List) into a collection (HashMap)
     *
     * @param list - the collection
     * @return  HashMap<Integer, User>  - the collection(HashMap).
     */
    HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> user = new HashMap<>();
        for (User x : list) {
            user.put(x.getId(), x);
        }
        return  user;
    }
}

package ru.job4j.storage;

import java.io.File;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 10.10.2019
 */
public interface Actions<T> {
    void add(File element);

    boolean clearAll();

    List<T> findByUser(String user);

    List<T> findByDevice(String device);

    List<T> findByType(String type);

    List<T> getAllJob();

    void close();
}

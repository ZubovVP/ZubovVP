package ru.job4j.connecrion;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.04.2020.
 */
public class StorageElement {
    private CopyOnWriteArrayList<CopyOnWriteArrayList<Element>> storage = new CopyOnWriteArrayList<>();


    private static StorageElement ourInstance = new StorageElement();

    public static StorageElement getInstance() {
        return ourInstance;
    }


    public CopyOnWriteArrayList<CopyOnWriteArrayList<Element>> getStorage() {
        return this.storage;
    }
    public void clearAll(){
        storage.clear();
    }

}

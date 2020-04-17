package ru.job4j.connecrion;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.04.2020.
 */
@ThreadSafe
public class Group implements GroupAble<Element> {
    @GuardedBy("storageElement")
    private StorageElement storageElement;

    public Group(StorageElement storageElement) {
        this.storageElement = storageElement;
    }

    @Override
    public boolean group(Element element) {
        boolean done = false;
        CopyOnWriteArrayList<CopyOnWriteArrayList<Element>> storage = this.storageElement.getStorage();
        Iterator<CopyOnWriteArrayList<Element>> itr = storage.listIterator();

        while (itr.hasNext() && !done) {
            CopyOnWriteArrayList<Element> e = itr.next();
            for (Element el : e) {
                if (el.equals(element)) {
                    done = e.add(element);
                    break;
                }
            }
        }
        if (!done) {
            CopyOnWriteArrayList<Element> a = new CopyOnWriteArrayList<>();
            a.add(element);
            storage.add(a);
        }
        return true;
    }

    public CopyOnWriteArrayList<CopyOnWriteArrayList<Element>> getStorage() {
        return this.storageElement.getStorage();
    }
}

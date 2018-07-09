package ru.job4j.blocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZubovVP on 23.06.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class NonBlockingCache {
    @GuardedBy("this")
    private ConcurrentHashMap<Integer, Base> store;

    /**
     * Constructor.
     */
    public NonBlockingCache() {
        this.store = new ConcurrentHashMap<>();
    }

    /**
     * Add element in the store.
     *
     * @param model - element.
     */
    public void add(Base model) {
        this.store.put(model.getId(), model);
    }

    /**
     * Delete element from the store.
     *
     * @param model
     */
    public void delete(Base model) {
        this.store.remove(model.getId(), model);
    }

    /**
     * Update element in the store.
     *
     * @param model
     */
    public void update(Base model) {
        this.store.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != this.store.get(model.getId()).getVersion()) {
                try {
                    throw new OptinisticException();
                } catch (OptinisticException e) {
                    e.printStackTrace();
                }
            }
            return model.incrementVersion();
        });
    }

    /**
     * Get store.
     *
     * @return - store.
     */
    public Map<Integer, Base> getStore() {
        return this.store;
    }
}
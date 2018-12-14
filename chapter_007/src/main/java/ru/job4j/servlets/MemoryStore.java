package ru.job4j.servlets;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 04.12.2018
 */
@ThreadSafe
public class MemoryStore implements Store {
    @GuardedBy("users")
    private ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final static MemoryStore MEMORY_STORE = new MemoryStore();
    private AtomicInteger id = new AtomicInteger(1);

    /**
     * Constructor.
     */
    private MemoryStore() {
    }

    /**
     * Constructor.
     *
     * @return - MemoryStore.
     */
    public static MemoryStore getInstance() {
        return MEMORY_STORE;
    }

    /**
     * Insert element in the users.
     *
     * @param user - user.
     * @return - result.
     */
    @Override
    public boolean add(User user) {
        user.setId(this.id.getAndIncrement());
        this.users.put(user.getId(), user);
        return true;
    }

    /**
     * Update element in the users.
     *
     * @param user - user.
     * @return - result.
     */
    @Override
    public boolean update(User user) {
        User oldUser = this.users.get(user.getId());
        user.setCreateDate(oldUser.getCreateDate());
        this.users.replace(user.getId(), user);
        return true;
    }

    /**
     * Delete element from the users.
     *
     * @param id - id of the user.
     * @return - result.
     */
    @Override
    public boolean delete(int id) {
        this.users.remove(id);
        return true;
    }

    /**
     * Find all the users from the users.
     *
     * @return - List of the users.
     */
    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.users.values());
    }

    /**
     * Find users with help id.
     *
     * @param id - id of user.
     * @return - user.
     */
    @Override
    public User findById(int id) {
        return this.users.get(id);
    }
}

package ru.job4j.synchro;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
@ThreadSafe
public class UserStorage {
        @GuardedBy("this")
        private Map<Integer, User> storage;

    /**
     * Constructor.
     */
    public UserStorage() {
        this.storage = new HashMap<>();
    }

    /**
     * Add user in the storage.
     *
     * @param user - user.
     * @return - result.
     */
    public boolean add(User user) {
        boolean result = false;
        synchronized (this) {
            if (!this.storage.containsKey(user.getId())) {
                this.storage.put(user.getId(), user);
                result = true;
            }
        }
        return result;
    }

    /**
     * Update user.
     *
     * @param user - user.
     * @return - result.
     */
    public boolean update(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.storage.containsKey(user.getId())) {
                this.storage.put(user.getId(), user);
                result = true;
            }
        }
        return result;
    }

    /**
     * Delete the user.
     *
     * @param user - user.
     * @return - result.
     */
    public boolean delete(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.storage.containsKey(user.getId())) {
                this.storage.remove(user.getId(), user);
                result = true;
            }
            return result;
        }
    }

    /**
     * Transfer between 2 users.
     *
     * @param fromId - id first user.
     * @param toId - if second user.
     * @param amount - amount.
     * @return - result.
     */
    public boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        int count;
        synchronized (this) {
            if (this.storage.containsKey(fromId) && this.storage.containsKey(toId) && this.storage.get(fromId).getAmount() >= amount) {
                count = this.storage.get(fromId).getAmount() - amount;
                this.storage.get(fromId).setAmount(count);
                count = this.storage.get(toId).getAmount() + amount;
                this.storage.get(toId).setAmount(count);
                result = true;
            }
            return result;
        }
    }

    /**
     * Get user.
     *
     * @param id - id of user.
     * @return - result.
     */
    public User getUser(int id) {
        synchronized (this) {
            return this.storage.get(id);
        }
    }
}

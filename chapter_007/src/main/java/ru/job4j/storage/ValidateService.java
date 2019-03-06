package ru.job4j.storage;

import ru.job4j.models.User;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 03.12.2018
 */
public class ValidateService implements Store<User> {
    private final static ValidateService VALIDATE_SERVICE = new ValidateService();
    private final Store<User> logic = DBStore.getInstance();

    /**
     * Return object ValidateService.
     *
     * @return ValidateService.
     */
    public static ValidateService getInstance() {
        return VALIDATE_SERVICE;
    }

    private ValidateService() {
    }

    /**
     * Check add user in the storage.
     *
     * @param user - user.
     */
    public boolean add(User user) {
        User userCorrect = checkUser(user);
        if (this.logic.getClass().getSimpleName().equals("MemoryStore")) {
            for (User us : this.logic.findAll()) {
                if (us.getLogin().equals(user.getLogin()) || us.getEmail().equals(user.getEmail())) {
                    throw new IncorrectDateException("User or Email are busy");
                }
            }
        }
        return this.logic.add(userCorrect);
    }

    /**
     * Check update user in the storage if true add user.
     *
     * @param user - user.
     */
    public boolean update(User user) {
        boolean result;
        if (user.getId() == 0) {
            throw new IncorrectDateException("User does not exist, please write correctly id");
        }
        User userCorrect = checkUser(user);
        if (this.logic.getClass().getSimpleName().equals("MemoryStore")) {
            for (User us : this.logic.findAll()) {
                if (user.getId() != us.getId() && (us.getLogin().equals(user.getLogin()) || us.getEmail().equals(user.getEmail()))) {
                    throw new IncorrectDateException("User or Email are busy");
                }
            }
        }
        result = logic.update(userCorrect);
        if (!result) {
            throw new IncorrectDateException("User does not exist");
        }
        return true;
    }

    /**
     * Check delete user in the storage if true delete user.
     */
    public boolean delete(int id) {
        if (!this.logic.delete(id)) {
            throw new IncorrectDateException("User isn't exist");
        }
        return true;
    }

    /**
     * Return all users from storage.
     *
     * @return - list of users.
     */
    public List<User> findAll() {
        return this.logic.findAll();
    }

    public User findById(int id) {
        User aimUser = this.logic.findById(id);
        if (aimUser == null) {
            throw new IncorrectDateException("User with id doesn't exist");
        }
        return aimUser;
    }

    /**
     * Check password.
     *
     * @param login    - login.
     * @param password - password.
     * @return - result.
     */
    @Override
    public boolean isCredentional(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            throw new IncorrectDateException("Login or password id Empty");
        }
        return this.logic.isCredentional(login, password);
    }

    /**
     * Check correct user.
     *
     * @param user - user
     * @return - result.
     */
    private User checkUser(User user) {
        if (user.getName().isEmpty() || user.getLogin().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            throw new IncorrectDateException("Fields name, login, email must be filled");
        }
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    /**
     * Clear users.
     */
    @Override
    public void close() {
        this.logic.close();
    }

    /**
     * Find user by login.
     *
     * @param login - login of user.
     * @return - user.
     */
    @Override
    public User findByLogin(String login) {
        return this.logic.findByLogin(login);
    }
}

package ru.job4j.servlets;

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
     * @throws IncorrectDateException
     */
    public boolean add(User user) throws IncorrectDateException {
        if (checkUser(user)) {
            for (User step : this.logic.findAll()) {
                if (step.getLogin().equals(user.getLogin()) || step.getEmail().equals(user.getEmail())) {
                    throw new IncorrectDateException("User with this login or email exists");
                }
            }
        }
        return this.logic.add(user);
    }

    /**
     * Check update user in the storage if true add user.
     *
     * @param user - user.
     */
    public boolean update(User user) throws IncorrectDateException {
        boolean result = false;
        if (checkUser(user)) {
            for (User step : this.logic.findAll()) {
                if (step.getId() != user.getId() && (step.getLogin().equals(user.getLogin()) || step.getEmail().equals(user.getEmail()))) {
                    throw new IncorrectDateException("Login or email were busy");
                }
            }
            for (User step : this.logic.findAll()) {
                if (step.getId() == user.getId()) {
                    result = true;
                    break;
                }
            }
        }
        if (!result) {
            throw new IncorrectDateException("User does not exist");
        }
            return this.logic.update(user);
    }

    /**
     * Check delete user in the storage if true delete user.
     *
     * @throws IncorrectDateException
     */
    public boolean delete(int id) throws IncorrectDateException {
        boolean result = false;
        List<User> users = this.logic.findAll();
        for (User user : users) {
            if (user.getId() == id) {
                result = this.logic.delete(id);
                break;
            }
        }
        if (!result) {
            throw new IncorrectDateException("User exist");
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

    public User findById(int id) throws IncorrectDateException {
        User aimUser = this.logic.findById(id);
        if (aimUser == null) {
            throw new IncorrectDateException("User with id doesn't exist");
        }
        return aimUser;
    }

    /**
     * Check correct user.
     *
     * @param user - user
     * @return - result.
     * @throws IncorrectDateException
     */
    private boolean checkUser(User user) throws IncorrectDateException {
        if (user.getName().isEmpty() || user.getLogin().isEmpty() || user.getEmail().isEmpty()) {
            throw new IncorrectDateException("Fields name, login, email must be filled");
        }
        return true;
    }
}

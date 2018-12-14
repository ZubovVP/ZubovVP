package ru.job4j.servlets;

import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov
 * Email: Zubov.VP@yandex.ru
 * Version: $Id$
 * Date: 03.12.2018
 */
public class ValidateService {
    private final static ValidateService VALIDATE_SERVICE = new ValidateService();
    private final Store logic = MemoryStore.getInstance();


    public static ValidateService getInstance() {
        return VALIDATE_SERVICE;
    }

    private ValidateService() {
    }

    public boolean add(User user) throws IncorrectDateException {
        if (checkUser(user)) {
            for (User step : this.logic.findAll()) {
                if (step.getLogin().equals(user.getLogin()) || step.getEmail().equals(user.getEmail())) {
                    throw new IncorrectDateException("User with this login or email exists");
                }
            }
        }
        this.logic.add(user);
        return true;
    }

    public boolean update(User user) throws IncorrectDateException {
        boolean result = false;
        if (checkUser(user)) {
            for (User step : this.logic.findAll()) {
                if (step.getId() == user.getId()) {
                    result = true;
                    break;
                }
            }
        }
        if (!result) {
            throw new IncorrectDateException("User does not exist");
        } else {
            this.logic.update(user);
        }
        return result;
    }

    public void delete(int id) throws IncorrectDateException {
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
    }

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

    private boolean checkUser(User user) throws IncorrectDateException {
        if (user.getName() == null || user.getLogin() == null || user.getEmail() == null) {
            throw new IncorrectDateException("Fields name, login, email must be filled");
        }
        return true;
    }
}

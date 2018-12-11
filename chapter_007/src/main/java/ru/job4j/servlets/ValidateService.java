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

    public boolean update(User user) {
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
            try {
                throw new IncorrectDateException("User does not exist");
            } catch (IncorrectDateException e) {
                e.printStackTrace();
            }
        } else {
            this.logic.update(user);
        }
        return result;
    }

    public void delete(int id) {
        boolean result = false;
        List<User> users = this.logic.findAll();
        for (User user : users) {
            if (user.getId() == id) {
                result = this.logic.delete(id);
                break;
            }
        }
        if (!result) {
            try {
                throw new IncorrectDateException("User exist");
            } catch (IncorrectDateException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> findAll() {
        return this.logic.findAll();
    }

    public User findById(int id) {
        User aimUser = this.logic.findById(id);
        if (aimUser == null) {
            try {
                throw new IncorrectDateException("User with id doesn't exist");
            } catch (IncorrectDateException e) {
                e.printStackTrace();
            }
        }
        return aimUser;
    }

    private boolean checkUser(User user) {
        if (user.getName() == null || user.getLogin() == null || user.getEmail() == null) {
            try {
                throw new IncorrectDateException("Fields name, login, email must be filled");
            } catch (IncorrectDateException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

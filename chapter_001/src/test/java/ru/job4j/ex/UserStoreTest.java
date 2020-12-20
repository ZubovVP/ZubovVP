package ru.job4j.ex;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.job4j.ex.UserStore.findUser;
import static ru.job4j.ex.UserStore.validate;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 20.12.2020.
 */
public class UserStoreTest {
    private User[] users;

    @Test(expected = UserInvalidException.class)
    public void testUseUserInvalidExceptionWithUserValidIsFalse() throws UserInvalidException {
        User user = new User("Vitaly Zubov", false);
        this.users = new User[]{user};
        if (validate(user)) {
            System.out.println("This user has an access");
        }
    }

    @Test(expected = UserInvalidException.class)
    public void testUseUserInvalidExceptionWithUserHasIsTooShortLogin() throws UserInvalidException {
        User user = new User("Vi", true);
        this.users = new User[]{user};
        if (validate(user)) {
            System.out.println("This user has an access");
        }
    }

    @Test(expected = UserNotFoundException.class)
    public void testDoNotFoundUserException() throws UserNotFoundException {
        User user = new User("Vitaly Zubov", true);
        this.users = new User[]{user};
        findUser(this.users,"Login");
    }
}

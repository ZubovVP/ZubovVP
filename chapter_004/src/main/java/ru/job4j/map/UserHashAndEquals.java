package ru.job4j.map;

import java.util.Calendar;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class UserHashAndEquals extends User {

    public UserHashAndEquals(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getChildren() != user.getChildren()) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return getBirthday() != null ? getBirthday().equals(user.getBirthday()) : user.getBirthday() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getChildren();
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }
}
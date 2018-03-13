package ru.job4j.convert;

import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {

    @Test
    public void userConvertTest() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(Arrays.asList(new User(1, "Alex", "Moscow"), new User(2, "Kate", "Kaluga"), new User(3, "Victor", "New York")));
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> test = userConvert.process(users);
        assertThat(test.get(1).getName(), is("Alex"));
        assertThat(test.get(2).getName(), is("Kate"));
        assertThat(test.get(3).getName(), is("Victor"));
    }
}
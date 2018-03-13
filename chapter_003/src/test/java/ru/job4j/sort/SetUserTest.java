package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class SetUserTest {

    @Test
    public void sortTest() throws Exception {
        ArrayList<User> test = new ArrayList<>();
        test.addAll(Arrays.asList(new User(25, "Duke"), new User(29, "Alex"), new User(18, "Mike")));
        SetUser setUser = new SetUser();
        Set<User> result = setUser.sort(test);
        assertThat(result.toArray()[0], is(new User(18, "Mike")));
        assertThat(result.toArray()[1], is(new User(25, "Duke")));
        assertThat(result.toArray()[2], is(new User(29, "Alex")));

    }

    @Test
    public void sortNameLengthTest() throws Exception {
        ArrayList<User> test = new ArrayList<>();
        test.addAll(Arrays.asList(new User(25, "George"), new User(29, "Ann"), new User(18, "Tony")));
        SetUser setUser = new SetUser();
        List<User> result = setUser.sortNameLength(test);
        assertThat(result.get(0).getName(), is(new User(29, "Ann").getName()));
        assertThat(result.get(1).getName(), is(new User(18, "Tony").getName()));
        assertThat(result.get(2).getName(), is(new User(25, "George").getName()));
    }

    @Test
    public void sortByAllFieldTest() throws Exception {
        ArrayList<User> test = new ArrayList<>();
        test.addAll(Arrays.asList(new User(25, "George"), new User(29, "Ann"), new User(18, "Tony"), new User(19, "Tony"), new User(21, "Tony")));
        SetUser setUser = new SetUser();
        List<User> result = setUser.sortNameLength(test);
        assertThat(result.get(0).getName(), is(new User(29, "Ann").getName()));
        assertThat(result.get(1).getName(), is(new User(18, "Tony").getName()));
        assertThat(result.get(2).getName(), is(new User(19, "Tony").getName()));
        assertThat(result.get(3).getName(), is(new User(21, "Tony").getName()));
        assertThat(result.get(4).getName(), is(new User(25, "George").getName()));
    }
}
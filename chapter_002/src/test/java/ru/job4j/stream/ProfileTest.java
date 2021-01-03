package ru.job4j.stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.01.2021.
 */
public class ProfileTest {
    private List<Profile> profiles = new ArrayList<>();

    @Before
    public void setUp() {
        profiles.add(new Profile(new Address("city", "street", 1, 1)));
        profiles.add(new Profile(new Address("city1", "street1", 1, 1)));
        profiles.add(new Profile(new Address("city", "street", 1, 1)));
        profiles.add(new Profile(new Address("city2", "street2", 1, 1)));
        profiles.add(new Profile(new Address("city", "street", 1, 1)));
        profiles.add(new Profile(new Address("city3", "street3", 1, 1)));
        profiles.add(new Profile(new Address("city3", "street3", 1, 1)));
        profiles.add(new Profile(new Address("city", "street", 1, 1)));

    }

    @Test
    public void testSizeAddress() {
        Assert.assertThat(Profile.collect(this.profiles).size(), is(4));
    }

    @Test
    public void testContainsElements() {
        List<Address> result = Profile.collect(this.profiles);
        Assert.assertThat(result.get(0).getCity(), is("city"));
        Assert.assertThat(result.get(1).getCity(), is("city1"));
        Assert.assertThat(result.get(2).getCity(), is("city2"));
        Assert.assertThat(result.get(3).getCity(), is("city3"));
    }
}
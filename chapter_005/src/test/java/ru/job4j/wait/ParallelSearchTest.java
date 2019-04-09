package ru.job4j.wait;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 19.06.2018
 * zubovvp@yadndex.ru
 */
public class ParallelSearchTest {

    @Test
    public void findNameBobShouldTwo() throws InterruptedException {
        List<String> exts = new ArrayList<>();
        exts.add("txt");
        ParallelSearch test1 = new ParallelSearch(new File("files").getAbsolutePath(), "Bob", exts);
        test1.init();
        assertThat(test1.result().size(), is(2));
    }

    @Test
    public void findNameDukeShouldOne() throws InterruptedException {
        List<String> exts = new ArrayList<>();
        exts.add("txt");
        ParallelSearch test2 = new ParallelSearch(new File("files").getAbsolutePath(), "Duke", exts);
        test2.init();
        assertThat(test2.result().size(), is(2));
    }

    @Test
    public void findNameSamShouldZero() throws InterruptedException {
        List<String> exts = new ArrayList<>();
        exts.add("txt");
        ParallelSearch test3 = new ParallelSearch(new File("files").getAbsolutePath(), "Sam", exts);
        test3.init();
        assertThat(test3.result().size(), is(0));
    }
}
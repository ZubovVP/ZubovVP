package ru.job4j.storage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Jobs;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.10.2019.
 */
public class DBServiceTest {
    private Jobs.Job job1 = new Jobs.Job("TestDevice1", "TestUser1", "copy", 5);
    private Jobs.Job job2 = new Jobs.Job("TestDevice2", "TestUser2", "print", 10);
    private DBService db;

    @Before
    public void start() {
        this.db = DBService.getInstance();
    }

    @Test
    public void getInstance() {
        assertThat(this.db.getClass().getSimpleName(), is("DBService"));
    }

    @Test
    public void testAddElementInDB() {
        this.db.clearAll();
        List<Jobs.Job> result = this.db.getAllJob();
        assertThat(result.size(), is(0));
        this.db.add(this.job1);
        result = this.db.getAllJob();
        assertThat(result.size(), is(1));
        this.db.add(this.job2);
        result = this.db.getAllJob();
        assertThat(result.size(), is(2));
        this.db.clearAll();
    }

    @Test
    public void clearAll() {
        this.db.clearAll();
        List<Jobs.Job> result = this.db.getAllJob();
        assertThat(result.size(), is(0));
        this.db.add(this.job1);
        result = this.db.getAllJob();
        assertThat(result.size(), is(1));
        this.db.clearAll();
        result = this.db.getAllJob();
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByUser() {
        this.db.clearAll();
        fillDB();
        assertThat(this.db.getAllJob().size(), is(2));
        List<Jobs.Job> result = this.db.findByUser(this.job1.getUser());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getUser(), is(this.job1.getUser()));
        result = this.db.findByUser(this.job2.getUser());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getUser(), is(this.job2.getUser()));
        this.db.clearAll();
    }

    @Test
    public void findByDevice() {
        this.db.clearAll();
        fillDB();
        assertThat(this.db.getAllJob().size(), is(2));
        List<Jobs.Job> result = this.db.findByDevice(this.job1.getDevice());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getDevice(), is(this.job1.getDevice()));
        result = this.db.findByDevice(this.job2.getDevice());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getDevice(), is(this.job2.getDevice()));
        this.db.clearAll();
    }


    @Test
    public void findByType() {
        this.db.clearAll();
        fillDB();
        assertThat(this.db.getAllJob().size(), is(2));
        List<Jobs.Job> result = this.db.findByType(this.job1.getType());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getType(), is(this.job1.getType()));
        result = this.db.findByType(this.job2.getType());
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getType(), is(this.job2.getType()));
        this.db.clearAll();
    }

    @Test
    public void getAllJob() {
        this.db.clearAll();
        fillDB();
        assertThat(this.db.getAllJob().size(), is(2));
        this.db.clearAll();
        assertThat(this.db.getAllJob().size(), is(0));
    }

    @Test
    public void testUncorrect() {
        Jobs.Job job = new Jobs.Job("", "userTest", "TypeTest", 20);
        this.db.add(job);
        assertThat(this.db.getAllJob().size(), is(0));

        job = new Jobs.Job("DeviceTest", "", "TypeTest", 20);
        this.db.add(job);
        assertThat(this.db.getAllJob().size(), is(0));

        job = new Jobs.Job("DeviceTest", "userTest", "", 20);
        this.db.add(job);
        assertThat(this.db.getAllJob().size(), is(0));
    }

    private void fillDB() {
        this.db.add(this.job1);
        this.db.add(this.job2);
    }
}
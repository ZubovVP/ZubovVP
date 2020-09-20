package ru.job4j.start;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.models.Item;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 07.09.2020.
 */
public class HbmTrackerTest {
    private HbmTracker tracker = new HbmTracker();

    @After
    public void finish() {
        this.tracker.close();
    }

    @Test
    public void addTest() {
        Item item = new Item();
        item.setName("name");
        item.setDescription("desc");
        item.setCreateOfDate(System.currentTimeMillis());
        this.tracker.add(item);
        List<Item> all = this.tracker.findAll();
        assertEquals(item, all.get(0));
    }

    @Test
    public void whenFindAll() {
        Item item = new Item();
        item.setName("name");
        item.setDescription("desc");
        item.setCreateOfDate(System.currentTimeMillis());
        Item item1 = new Item();
        item.setName("name1");
        item.setDescription("desc2");
        item.setCreateOfDate(System.currentTimeMillis());
        this.tracker.add(item);
        this.tracker.add(item1);
        assertEquals(List.of(item, item1), this.tracker.findAll());
    }

    @Test
    public void whenDeleteItem() {
        Item item = new Item();
        item.setName("name");
        item.setDescription("desc");
        item.setCreateOfDate(System.currentTimeMillis());
        Item item1 = new Item();
        item.setName("name1");
        item.setDescription("desc2");
        item.setCreateOfDate(System.currentTimeMillis());
        this.tracker.add(item);
        this.tracker.add(item1);
        Assert.assertThat(this.tracker.findAll().size(), is(2));
        this.tracker.delete(item.getId());
        Assert.assertThat(this.tracker.findAll().size(), is(1));
        assertEquals(List.of(item1), this.tracker.findAll());
    }

    @Test
    public void whenReplaceItem() {
        Item item = new Item();
        item.setName("name");
        item.setDescription("desc");
        item.setCreateOfDate(System.currentTimeMillis());
        this.tracker.add(item);
        assertEquals(List.of(item), this.tracker.findAll());
        item.setName("name2");
        this.tracker.replace(item);
        assertEquals(List.of(item), this.tracker.findAll());
    }

    @Test
    public void whenFindByIdItem() {
        Item item = new Item();
        item.setName("name");
        item.setDescription("desc");
        item.setCreateOfDate(System.currentTimeMillis());
        Item item1 = new Item();
        item.setName("name1");
        item.setDescription("desc2");
        item.setCreateOfDate(System.currentTimeMillis());
        this.tracker.add(item);
        this.tracker.add(item1);
        Assert.assertThat(this.tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenFindByNameItem() {
        Item item = new Item();
        item.setName("name");
        item.setDescription("desc");
        item.setCreateOfDate(System.currentTimeMillis());
        Item item1 = new Item();
        item.setName("name1");
        item.setDescription("desc2");
        item.setCreateOfDate(System.currentTimeMillis());
        this.tracker.add(item);
        this.tracker.add(item1);
        Assert.assertThat(this.tracker.findByName(item1.getName()).size(), is(1));
        assertEquals(List.of(item1), this.tracker.findByName(item1.getName()));
    }
}
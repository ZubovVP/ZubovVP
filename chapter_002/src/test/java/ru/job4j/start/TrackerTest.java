package ru.job4j.start;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Item;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

/**
 * add test
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    @Before
    public void start() {
        try (Tracker tracker = new Tracker()) {
            List<Item> result = tracker.findAll();
            for (Item item : result) {
                tracker.delete(item.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void finish() {
        try (Tracker tracker = new Tracker()) {
            List<Item> result = tracker.findAll();
            for (Item item : result) {
                tracker.delete(item.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Test.
     */
    @Test
    public void addNewItemGetThisItemCompareItemsShouldTrue() {
        Item item = new Item("Test_Name", "Test_Desc", System.currentTimeMillis());
        Item result;
        try (Tracker tracker = new Tracker()) {
            tracker.add(item);
            result = tracker.findByName(item.getName()).get(0);
            tracker.delete(result.getId());
            assertThat(result.getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Test.
     */
    @Test
    public void addItemCheckFindByIdShouldTrue() {
        Item item = new Item("Test_Name", "Test_Desc", System.currentTimeMillis());
        Item result;
        try (Tracker tracker = new Tracker()) {
            tracker.add(item);
            int id = tracker.findAll().get(0).getId();
            result = tracker.findById(id);
            tracker.delete(result.getId());
            assertThat(result.getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Test.
     */
    @Test
    public void addItemAndFindByNameItemAndCompareShouldTrue() {
        Item item = new Item("Test_Name11", "Test_Desc11", System.currentTimeMillis());
        try (Tracker tracker = new Tracker()) {
            tracker.add(item);
            Item result = tracker.findByName(item.getName()).get(0);
            assertThat(result.getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Test
     */
    @Test
    public void addItemAndFindByIdItemAndCompareShouldTrue() {
        Item item = new Item("Test_Name11", "Test_Desc11", System.currentTimeMillis());
        try (Tracker tracker = new Tracker()) {
            tracker.add(item);
            int id = tracker.findAll().get(0).getId();
            Item result = tracker.findById(id);
            assertThat(result.getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Test.
     */
    @Test
    public void whenWeDeleteItem() {
        Item item = new Item("Test_Name", "Test_Desc", System.currentTimeMillis());
        Item result;
        try (Tracker tracker = new Tracker()) {
            tracker.add(item);
            int id = tracker.findAll().get(0).getId();
            result = tracker.findById(id);
            assertThat(result.getName(), is(item.getName()));
            tracker.delete(result.getId());
            result = tracker.findById(item.getId());
            assertNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Test
     */
    @Test
    public void addItemAndReplaceItemAndCompareShouldTrue() {
        Item item = new Item("Test_Name11", "Test_Desc11", System.currentTimeMillis());
        try (Tracker tracker = new Tracker()) {
            tracker.add(item);
            item.setName("Test_Name10");
            item.setId(tracker.findAll().get(0).getId());
            tracker.replace(item);
            Item result = tracker.findByName(item.getName()).get(0);
            assertThat(result.getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Test
     */
    @Test
    public void addGetAll() {
        Item item = new Item("Test_Name11", "Test_Desc11", System.currentTimeMillis());
        try (Tracker tracker = new Tracker()) {
            tracker.add(item);
            List<Item> result = tracker.findAll();
            tracker.delete(item.getId());
            assertThat(result.size(), is(1));
            assertThat(result.get(0).getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkConnection() {
        Tracker sql = new Tracker();
        assertThat(sql.connect(), is(true));
    }
}
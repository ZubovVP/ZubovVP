package ru.job4j.start;


import org.junit.Test;
import ru.job4j.models.Item;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;

/**
 * add test
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
    public class TrackerTest {
        /**
         * Add Test.
         */
        @Test
        public void whenAddNewItemThenTrackerHasSameItem() {
            //Создём объект tracker
            Tracker tracker = new Tracker();
            //Cоздаём объект itemOne и инициализируем его поля
            Item itemOne = new Item("test1", "testDescriprion", 123L);
            // добаляем объект itemOne в массив items
            tracker.add(itemOne);
            //выводим все объекты из массива items и сверяем с добавленными объектами
            assertThat(tracker.getAll()[0], is(itemOne));
        }

    /**
     * Add Test.
     */
        @Test
        public void whenCheckIdItemOnewithIdItemTwo() {
            //Создём объект tracker
            Tracker tracker = new Tracker();
            //Cоздаём объект itemOne и инициализируем его поля
            Item item = new Item("test1", "testDescriprion", 123L);
            // добаляем объект itemOne в массив items
            tracker.add(item);
            //Cоздаём объект itemTwo и инициализируем его поля
            Item itemTwo = new Item("test2", "testDescriprion2", 456L);
            // добаляем объект itemTwo в массив items
            tracker.add(itemTwo);
            //Проверяем что у каждого объекта присвоен свой id
            assertNotEquals(item.getId(), itemTwo.getId());
        }

    /**
     * Add Test.
     */
        @Test
        public void whenUpdateItemOneOnItemTwo() {
            //Создём объект tracker
            Tracker tracker = new Tracker();
            //Cоздаём объект itemOne и инициализируем его поля
            Item itemOne = new Item("test1", "testDescriprion", 123L);
            // добаляем объект itemOne в массив items
            tracker.add(itemOne);
            //Cоздаём объект itemTwo и инициализируем его поля
            Item itemTwo = new Item("test2", "testDescriprion2", 456L);
            // добаляем id для объекта itemTwo(получается что один id на который ссылаются два объекта(itemOne и ItemTwo))
            itemTwo.setId(itemOne.getId());
            //изменяем объект в массиве по id
            tracker.replace(itemTwo);
            //Проверяем что данный объект сооветствует ожиданию
            assertEquals(tracker.getAll()[0], itemTwo);
        }

    /**
     * Add Test.
     */
        @Test
        public void whenWeDeleteItem() {
            //Создём объект tracker
            Tracker tracker = new Tracker();
            //Cоздаём объект itemOne и инициализируем его поля
            Item itemOne = new Item("test1", "testDescriprion", 123L);
            // добаляем объект itemOne в массив items
            tracker.add(itemOne);
            //Cоздаём объект itemTwo и инициализируем его поля
            Item itemTwo = new Item("test2", "testDescriprion2", 1234L);
            // добаляем id для объекта itemTwo в массив items
            tracker.add(itemTwo);
            //Удаляем объект itemOne по id
            tracker.delete(tracker.getAll()[0].getId());
            //проверяем что в итоге остаётся в массиве items после удаления
            assertEquals(tracker.getAll()[0], itemTwo);
        }
    }

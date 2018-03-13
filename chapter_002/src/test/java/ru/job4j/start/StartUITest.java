package ru.job4j.start;

import org.junit.Test;
import ru.job4j.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * add test
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {
    /**
     * Test
     */
        @Test
        public void whenUserAddItem() {
            Tracker tracker = new Tracker();
            Input input = new StubInput(new String[]{"0", "TestName1", "TestDescr1", "no", "0", "TestName2", "TestDescr2", "Yes"});
            new StartUI(input, tracker).init();
            assertThat(tracker.getAll()[1].getName(), is("TestName2"));
            assertThat(tracker.getAll()[1].getDescription(), is("TestDescr2"));
        }

    /**
     *Test
     */
        @Test
        public void whenUserEditItem() {
            Tracker tracker = new Tracker();
            Item item = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            Input input = new StubInput(new String[]{"0", "TestName2", "TestDesc2", "no", "0", "TestName3", "TestDesc3", "no", "2", item.getId(), "test name4", "desc4", "Yes"});
            new StartUI(input, tracker).init();
            assertThat(tracker.getAll()[0].getName(), is("test name4"));
            assertThat(tracker.getAll()[0].getDescription(), is("desc4"));
        }

    /**
     * Test
     */
        @Test
        public void whemUserDeleteItem() {
            Tracker tracker = new Tracker();
            Item item = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            Input input = new StubInput(new String[]{"0", "TestName2", "TestDesc2", "no", "0", "TestName3", "TestDesc3", "no", "3",  item.getId(), "Yes"});
            new StartUI(input, tracker).init();
            assertThat(tracker.getAll()[0].getName(), is("TestName2"));
            assertThat(tracker.getAll()[0].getDescription(), is("TestDesc2"));
        }

    /**
     * Test
     */
        @Test
        public void whenUserFindItemById() {
            Tracker tracker = new Tracker();
            Item item = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            // получаем ссылку на стандартный вывод в консоль.
            PrintStream stdout = System.out;
            // Создаем буфер для хранения вывода.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Заменяем стандартный вывод на вывод в пямять для тестирования.
            System.setOut(new PrintStream(out));
            // выполняем действия пишушиее в консоль.
            Input input = new StubInput(new String[]{"4",  item.getId(), "Yes"});
            new StartUI(input, tracker).init();
            assertThat(new String(out.toByteArray()), is(new StringBuilder()
                    .append("0. Add the new Item. " + System.getProperty("line.separator")
                            + "1. Show all Items. " + System.getProperty("line.separator")
                            + "2. Edit item. " + System.getProperty("line.separator")
                            + "3. Delete item. " + System.getProperty("line.separator")
                            + "4. Find by id. " + System.getProperty("line.separator")
                            + "5. Find item by name. " + System.getProperty("line.separator")
                            + "Delete"  + System.getProperty("line.separator"))
                    .append(System.getProperty("line.separator") + " Name : " + item.getName() + ";"
                            + System.getProperty("line.separator") + "Description :" + item.getDescription() + ";"
                            + System.getProperty("line.separator") + "Id :" + item.getId() + ";"
                            + System.getProperty("line.separator") + "Create :" + item.getCreate() + ";"
                            + System.getProperty("line.separator")).toString()));
        }

    /**
     * Test
     */
        @Test
        public void whenUserFindItemByName() {
            Tracker tracker = new Tracker();
            Item item = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            // получаем ссылку на стандартный вывод в консоль.
            PrintStream stdout = System.out;
            // Создаем буфер для хранения вывода.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Заменяем стандартный вывод на вывод в пямять для тестирования.
            System.setOut(new PrintStream(out));
            // выполняем действия пишушиее в консоль.
            Input input = new StubInput(new String[]{"5",  item.getName(), "Yes"});
            new StartUI(input, tracker).init();
            assertThat(new String(out.toByteArray()), is(new StringBuilder()
                    .append("0. Add the new Item. " + System.getProperty("line.separator")
                            + "1. Show all Items. " + System.getProperty("line.separator")
                            + "2. Edit item. " + System.getProperty("line.separator")
                            + "3. Delete item. " + System.getProperty("line.separator")
                            + "4. Find by id. " + System.getProperty("line.separator")
                            + "5. Find item by name. " + System.getProperty("line.separator")
                            + "Delete"  + System.getProperty("line.separator"))
                    .append(System.getProperty("line.separator") + " Name : " + item.getName() + ";"
                            + System.getProperty("line.separator") + "Description :" + item.getDescription() + ";"
                            + System.getProperty("line.separator") + "Id :" + item.getId() + ";"
                            + System.getProperty("line.separator") + "Create :" + item.getCreate() + ";"
                            + System.getProperty("line.separator")).toString()));
        }

    /**
     * Test
     */
        @Test
    public void whenGetAllItems() {
            Tracker tracker = new Tracker();
            Item itemOne = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            Item itemTwo = tracker.add(new Item("TestName2", "TestDesc2", System.currentTimeMillis()));
            // получаем ссылку на стандартный вывод в консоль.
            PrintStream stdout = System.out;
            // Создаем буфер для хранения вывода.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Заменяем стандартный вывод на вывод в пямять для тестирования.
            System.setOut(new PrintStream(out));
            // выполняем действия пишушиее в консоль.
            Input input = new StubInput(new String[]{"1", "Yes"});
            new StartUI(input, tracker).init();
            assertThat(new String(out.toByteArray()), is(new StringBuilder()
                    .append("0. Add the new Item. " + System.getProperty("line.separator")
                                    + "1. Show all Items. " + System.getProperty("line.separator")
                                    + "2. Edit item. " + System.getProperty("line.separator")
                                    + "3. Delete item. " + System.getProperty("line.separator")
                                    + "4. Find by id. " + System.getProperty("line.separator")
                                    + "5. Find item by name. " + System.getProperty("line.separator")
                                    + "Delete"  + System.getProperty("line.separator"))
                    .append(System.getProperty("line.separator") + " Name : " + itemOne.getName() + ";"
                            + System.getProperty("line.separator") + "Description :" + itemOne.getDescription() + ";"
                            + System.getProperty("line.separator") + "Id :" + itemOne.getId() + ";"
                            + System.getProperty("line.separator") + "Create :" + itemOne.getCreate() + ";"
                            + System.getProperty("line.separator"))
                    .append(System.getProperty("line.separator") + " Name : " + itemTwo.getName() + ";"
                            + System.getProperty("line.separator") + "Description :" + itemTwo.getDescription() + ";"
                            + System.getProperty("line.separator") + "Id :" + itemTwo.getId() + ";"
                            + System.getProperty("line.separator") + "Create :" + itemTwo.getCreate() + ";"
                            + System.getProperty("line.separator")).toString()));
        }

    }

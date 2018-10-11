package ru.job4j.start;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * add test
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {
    private Config config = new Config();

    /**
     * Test COLL
     */
    @Test
    public void whenUserAddItem() {
        try (Tracker tracker = new Tracker(config)) {
            Input input1 = new StubInput(new String[]{"0", "TestName1", "TestDescr1", "no", "0", "TestName2", "TestDescr2", "Yes"});
            new StartUI(input1, tracker).init();
            Item result1 = tracker.getAll().get(0);
            Item result2 = tracker.getAll().get(1);
            Input input2 = new StubInput(new String[]{"3", result1.getId(), "no", "3", result2.getId(), "Yes"});
            new StartUI(input2, tracker).init();
            assertThat(result2.getName(), is("TestName2"));
            assertThat(result2.getDescription(), is("TestDescr2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test
     */
    @Ignore
    public void whenUserEditItem() {
        try (Tracker tracker = new Tracker(config)) {
            Item item = new Item("TestName1", "TestDesc1", System.currentTimeMillis());

            Input input1 = new StubInput(new String[]{"0", item.getName(), item.getDescription(), "no", "0", "TestName2", "TestDesc2", "no", "2", item.getId(), "TestName4", "TestDesc4", "Yes"});
            new StartUI(input1, tracker).init();

            List<Item> result = tracker.getAll();
            for (Item it : result) {
                System.out.println(it.getName());
            }

            Input input2 = new StubInput(new String[]{"3", result.get(0).getId(), "no", "3", result.get(1).getId(), "no", "3", result.get(2).getId(), "Yes"});
            new StartUI(input2, tracker).init();

            assertThat(result.get(0).getName(), is("test name4"));
            assertThat(result.get(0).getDescription(), is("desc4"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test COLL
     */
    @Test
    public void whemUserDeleteItem() {
        try (Tracker tracker = new Tracker(config)) {
            Item item = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            Input input1 = new StubInput(new String[]{"0", "TestName2", "TestDesc2", "no", "0", "TestName3", "TestDesc3", "no", "3", item.getId(), "Yes"});
            new StartUI(input1, tracker).init();
            Item result1 = tracker.getAll().get(0);
            Item result2 = tracker.getAll().get(1);
            assertThat(result1.getName(), is("TestName2"));
            assertThat(result2.getDescription(), is("TestDesc3"));
            Input input2 = new StubInput(new String[]{"3", result1.getId(), "no", "3", result2.getId(), "Yes"});
            new StartUI(input2, tracker).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test
     */
    @Test
    public void whenUserFindItemById() {
        try (Tracker tracker = new Tracker(config)) {
            Item item = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            // получаем ссылку на стандартный вывод в консоль.
            PrintStream stdout = System.out;
            // Создаем буфер для хранения вывода.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Заменяем стандартный вывод на вывод в пямять для тестирования.
            System.setOut(new PrintStream(out));
            // выполняем действия пишушиее в консоль.
            Input input = new StubInput(new String[]{"4", item.getId(), "Yes"});
            new StartUI(input, tracker).init();
            assertThat(new String(out.toByteArray()), is(new StringBuilder()
                    .append("0. Add the new Item. " + System.getProperty("line.separator")
                            + "1. Show all Items. " + System.getProperty("line.separator")
                            + "2. Edit item. " + System.getProperty("line.separator")
                            + "3. Delete item. " + System.getProperty("line.separator")
                            + "4. Find by id. " + System.getProperty("line.separator")
                            + "5. Find item by name. " + System.getProperty("line.separator")
                            + "Delete" + System.getProperty("line.separator"))
                    .append(System.getProperty("line.separator") + " Name : " + item.getName() + ";"
                            + System.getProperty("line.separator") + "Description :" + item.getDescription() + ";"
                            + System.getProperty("line.separator") + "Id :" + item.getId() + ";"
                            + System.getProperty("line.separator") + "Create :" + item.getCreate() + ";"
                            + System.getProperty("line.separator")).toString()));
            tracker.delete(item.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test
     */
    @Test
    public void whenUserFindItemByName() {
        try (Tracker tracker = new Tracker(config)) {
            Item item = tracker.add(new Item("TestName1", "TestDesc1", System.currentTimeMillis()));
            // получаем ссылку на стандартный вывод в консоль.
            PrintStream stdout = System.out;
            // Создаем буфер для хранения вывода.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //Заменяем стандартный вывод на вывод в пямять для тестирования.
            System.setOut(new PrintStream(out));
            // выполняем действия пишушиее в консоль.
            Input input = new StubInput(new String[]{"5", item.getName(), "Yes"});
            new StartUI(input, tracker).init();
            assertThat(new String(out.toByteArray()), is(new StringBuilder()
                    .append("0. Add the new Item. " + System.getProperty("line.separator")
                            + "1. Show all Items. " + System.getProperty("line.separator")
                            + "2. Edit item. " + System.getProperty("line.separator")
                            + "3. Delete item. " + System.getProperty("line.separator")
                            + "4. Find by id. " + System.getProperty("line.separator")
                            + "5. Find item by name. " + System.getProperty("line.separator")
                            + "Delete" + System.getProperty("line.separator"))
                    .append(System.getProperty("line.separator") + " Name : " + item.getName() + ";"
                            + System.getProperty("line.separator") + "Description :" + item.getDescription() + ";"
                            + System.getProperty("line.separator") + "Id :" + item.getId() + ";"
                            + System.getProperty("line.separator") + "Create :" + item.getCreate() + ";"
                            + System.getProperty("line.separator")).toString()));
            tracker.delete(item.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test
     */
    @Test
    public void whenGetAllItems() {
        try (Tracker tracker = new Tracker(config)) {
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
                            + "Delete" + System.getProperty("line.separator"))
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
            tracker.delete(itemOne.getId());
            tracker.delete(itemTwo.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

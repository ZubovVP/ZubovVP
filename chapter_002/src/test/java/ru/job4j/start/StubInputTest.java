package ru.job4j.start;

import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StubInputTest {
    @Test
    public void whenUserAddItem() {
        Tracker tracker = new Tracker();
         Input input = new StubInput(new String[]{"0", "test name", "desc" , "no", "0", "test name2", "desc2", "Yes"});
         new StartUI(input, tracker).init();
         assertThat(tracker.getAll()[1].getName(), is("test name2"));
         assertThat(tracker.getAll()[1].getDescription(), is("desc2"));
    }

    @Test
    public void whenUserEditItem(){
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "no", "0", "TestName2", "TestDesc2", "no", "0", "TestName3", "TestDesc3", "no", "2", tracker.getAll()[0].getId(), "test name4", "desc4", "Yes"});//Создаём последовательность действий
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name4"));
        assertThat(tracker.getAll()[0].getDescription(), is("desc4"));
    }

    @Test
    public void whemUserDeleteItem(){
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "no", "0", "TestName2", "TestDesc2", "no", "0", "TestName3", "TestDesc3", "no", "3",  tracker.getAll()[0].getId(), "Yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[1].getName(), is("TestName3"));
        assertThat(tracker.getAll()[1].getDescription(), is("TestDesc3"));
    }

    @Test
    public void whenUserFindItemById(){
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "no", "0", "TestName2", "TestDesc2", "no", "4",  tracker.getAll()[1].getId(), "Yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[1].getName(), is("TestName2"));
        assertThat(tracker.getAll()[1].getDescription(), is("TestDesc2"));
    }

    @Test
    public void whenUserFindItemByName(){
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "no", "0", "TestName2", "TestDesc2", "no", "0", "TestName3", "TestDesc3", "no", "5",  tracker.getAll()[1].getName(), "Yes"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[1].getDescription(), is("TestDesc2"));
    }
}
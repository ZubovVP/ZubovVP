package ru.job4j.start;

import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StubInputTest {
    @Test
    public void whenUserAddItem() {
        Tracker tracker = new Tracker(); //Создаём Трекер
         Input input = new StubInput(new String[]{"0", "test name", "desc" , "0", "test name2", "desc2", "6"});//Создаём последовательность действий
         new StartUI(input, tracker).init();//Создаём StubInput и вызываем метод init()
         assertThat(tracker.getAll()[1].getName(), is("test name2"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        assertThat(tracker.getAll()[1].getDescription(), is("desc2"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
    }
    @Test
    public void whenUserEditItem(){
        Tracker tracker = new Tracker(); //Создаём Трекер
        Item item = tracker.add(new Item());//Напрямую добавляем заявку
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "0", "TestName2", "TestDesc2","0", "TestName3", "TestDesc3", "2", tracker.getAll()[1].getId(), "test name4", "desc4", "6"});//Создаём последовательность действий
        new StartUI(input, tracker).init();//Создаём StubInput и вызываем метод init()
        assertThat(tracker.getAll()[1].getName(), is("test name4"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        assertThat(tracker.getAll()[1].getDescription(), is("desc4"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        //Если я данную операцию проделаю с нулевым элементом массива, то тест пройдёт, а с первым элементом не продучается, ошибка - java.lang.ArrayIndexOutOfBoundsException: 1, хотя я выхожу из цикла StartUI
    }
    @Test
    public void whemUserDeleteItem(){
        Tracker tracker = new Tracker(); //Создаём Трекер
        Item item = tracker.add(new Item());//Напрямую добавляем заявку
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "0", "TestName2", "TestDesc2", "0", "TestName3", "TestDesc3", "3",  tracker.getAll()[1].getId(), "6"});//Создаём последовательность действий
        new StartUI(input, tracker).init();//Создаём StubInput и вызываем метод init()
        assertThat(tracker.getAll()[1].getName(), is("TestName2"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        assertThat(tracker.getAll()[1].getDescription(), is("TestDesc2"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        //Если я данную операцию проделаю с нулевым элементом массива, то тест показывает что актуальная - Actual   :TestName1, хотя я его удалил и сдвинул массив а с первым элементом не продучается, ошибка - java.lang.ArrayIndexOutOfBoundsException: 1, хотя я выхожу из цикла StartUI
    }
    @Test
    public void whenUserFindItemById(){
        Tracker tracker = new Tracker(); //Создаём Трекер
        Item item = tracker.add(new Item());//Напрямую добавляем заявку
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "0", "TestName2", "TestDesc2", "4",  tracker.getAll()[1].getId(), "6"});//Создаём последовательность действий
        new StartUI(input, tracker).init();//Создаём StubInput и вызываем метод init()
        assertThat(tracker.getAll()[1].getName(), is("TestName2"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        assertThat(tracker.getAll()[1].getDescription(), is("TestDesc2"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        // Если я данную операцию проделаю с нулевым элементом массива, то тест показывает что актуальная - Actual   :null, но там явно не null, при 1, ошибка - java.lang.ArrayIndexOutOfBoundsException: 1, хотя я выхожу из цикла StartUI
    }
    @Test
    public void whenUserFindItemByName(){
        Tracker tracker = new Tracker(); //Создаём Трекер
        Item item = tracker.add(new Item());//Напрямую добавляем заявку
        Input input = new StubInput(new String[]{"0", "TestName1", "TestDesc1", "0", "TestName2", "TestDesc2", "0", "TestName3", "TestDesc3", "5",  tracker.getAll()[0].getName(), "6"});//Создаём последовательность действий
        new StartUI(input, tracker).init();//Создаём StubInput и вызываем метод init()
        assertThat(tracker.getAll()[1].getDescription(), is("TestDesc1"));//проверяем что 1ый элемент массива в трекере содержит соответствущие данные
        // Если я данную операцию проделаю с нулевым элементом массива, то тест показывает чjava.lang.NullPointerException, при 1, ошибка - java.lang.NullPointerException
    }
}
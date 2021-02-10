package ru.job4j.synchro;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class UserStorageTest {
    private  UserStorage storage = new UserStorage();
    private User userTestOne = new User(1, 10);

    @Before
    public void start() {
        this.storage.add(userTestOne);
    }

    @Test
    public void add() throws Exception {
        assertTrue(this.storage.add(new User(2, 50)));
        assertFalse(this.storage.add(new User(2, 100)));
        assertThat(this.storage.getUser(2).getAmount(), is(50));
        /*В этом методе можно было протестировать с двумя потоками, но т.к. я не могу предугадать какой из потоков выполнит
        операцию первый(если id занят другим юзером, то данный метод просто не выполняется и выбрасывает false) соответственно
         я не могу предугать какой из потоков заполнил хранилще первым чтобы потом проверитьчто будет в хранилище.*/
    }

    @Test
    public void update() throws Exception {
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                userTestOne.setAmount(userTestOne.getAmount() + 10);
               assertTrue(storage.update(userTestOne));
               assertFalse(storage.update(new User(100, 100)));
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                userTestOne.setAmount(userTestOne.getAmount() + 10);
                assertTrue(storage.update(userTestOne));
                assertFalse(storage.update(new User(100, 100)));
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(this.storage.getUser(1).getAmount(), is(30));
    }

    @Test
    public void delete() throws Exception {
        assertTrue(this.storage.delete(userTestOne));
        assertFalse(this.storage.delete(userTestOne));
        assertNull(this.storage.getUser(userTestOne.getId()));
        // тут аналогия с методом add.

    }

    @Test
    public void transfer() throws Exception {
        User userTestTwo = new User(2, 50);
        User userTestThree = new User(3, 100);
        this.storage.add(userTestTwo);
        this.storage.add(userTestThree);


        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                assertTrue(storage.transfer(userTestOne.getId(), userTestThree.getId(), 10));
                assertFalse(storage.transfer(userTestOne.getId(), userTestThree.getId(), 1000));
            }
        });

        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
               assertTrue(storage.transfer(userTestTwo.getId(), userTestThree.getId(), 10));
               assertFalse(storage.transfer(userTestOne.getId(), userTestThree.getId(), 1000));

            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(storage.getUser(userTestThree.getId()).getAmount(), is(120));
    }
}
package ru.job4j.bank;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class BankTest {
   private Bank bankTest = new Bank();
   private Map<User, List<Account>> mapUserTest;

    @Test
    public void addUserTest() {
        User userTest = new User("Alex", "123456");
        bankTest.addUser(userTest);
        mapUserTest = bankTest.getMapUser();
        assertTrue(mapUserTest.containsKey(userTest));
    }

    @Test
    public void deleteUserTest() {
        User userTest = new User("Alex", "123456");
        bankTest.addUser(userTest);
        mapUserTest = bankTest.getMapUser();
        assertTrue(mapUserTest.containsKey(userTest));
        bankTest.deleteUser(userTest);
        mapUserTest = bankTest.getMapUser();
        assertFalse(mapUserTest.containsKey(userTest));
    }

    @Test
    public void addAccountToUserTest() {
        Account accountTest = new Account(50, "1234");
        User userTest = new User("Alex", "123456");
        bankTest.addUser(userTest);
        bankTest.addAccountToUser(userTest.getPasport(), accountTest);
        mapUserTest = bankTest.getMapUser();
        assertThat(mapUserTest.get(userTest).get(0), is(accountTest));
    }

    @Test
    public void deleteAccountFromUserTest() {
        User userTest = new User("Alex", "123456");
        Account accountTestOne = new Account(50, "1234");
        Account accountTestTwo = new Account(100, "12345");
        bankTest.addUser(userTest);
        bankTest.addAccountToUser(userTest.getPasport(), accountTestOne);
        bankTest.addAccountToUser(userTest.getPasport(), accountTestTwo);
        mapUserTest = bankTest.getMapUser();
        assertThat(mapUserTest.get(userTest).get(0), is(accountTestOne));
        assertThat(mapUserTest.get(userTest).get(1), is(accountTestTwo));
        bankTest.deleteAccountFromUser(userTest.getPasport(), accountTestOne);
        assertThat(mapUserTest.get(userTest).get(0), is(accountTestTwo));
    }

    @Test
    public void transferMoneyTest() {
        boolean result;
        User userTestOne = new User("Alex", "123456");
        User userTestTwo = new User("Kate", "654321");
        Account accountTestOne = new Account(50, "1234");
        Account accountTestTwo = new Account(100, "12345");
        bankTest.addUser(userTestOne);
        bankTest.addUser(userTestTwo);
        bankTest.addAccountToUser(userTestOne.getPasport(), accountTestOne);
        bankTest.addAccountToUser(userTestTwo.getPasport(), accountTestTwo);
        result = bankTest.transferMoney(userTestTwo.getPasport(), accountTestTwo.getRequisites(), userTestOne.getPasport(), accountTestOne.getRequisites(), 25);
        assertTrue(result);
        mapUserTest = bankTest.getMapUser();
        assertThat(mapUserTest.get(userTestOne).get(0).getValue(), is(75));
        assertThat(mapUserTest.get(userTestTwo).get(0).getValue(), is(75));
    }
}
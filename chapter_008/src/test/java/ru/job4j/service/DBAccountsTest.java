package ru.job4j.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Account;
import ru.job4j.persistence.DBAccounts;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 31.07.2019.
 */
public class DBAccountsTest {
    private DBAccounts dbAccounts;

    @Before
    public void start() {
        this.dbAccounts = DBAccounts.getInstance();
    }

    @After
    public void finish() {
        this.dbAccounts.clearAll();
    }

    @Test
    public void getInstanceTest() {
        assertThat(dbAccounts.getClass().getSimpleName(), is("DBAccounts"));
    }

    @Test
    public void addElementInTheDatabaseCheckThisElement() {
        Account account = new Account("Duke", "123");
        Account accountFromDB = this.dbAccounts.get(account.getPhone());
        assertNull(accountFromDB);
        accountFromDB = this.dbAccounts.add(account);
        assertNotNull(accountFromDB);
        assertThat(account.getName(), is(accountFromDB.getName()));
        this.dbAccounts.delete(accountFromDB.getId());
        accountFromDB = this.dbAccounts.get(account.getPhone());
        assertNull(accountFromDB);
    }

    @Test
    public void getElementFromDatabaseAndCheck() {
        Account account = new Account("Duke", "123");
        Account accountFromDB;
        this.dbAccounts.add(account);
        accountFromDB = this.dbAccounts.get(account.getPhone());
        assertNotNull(accountFromDB);
        assertThat(account.getName(), is(accountFromDB.getName()));
        assertThat(account.getPhone(), is(accountFromDB.getPhone()));
        this.dbAccounts.delete(accountFromDB.getId());
    }

    @Test
    public void addElementGetElementFromDatabaseCorrectElementUpdateElementInTheDatabaseAndCheck() {
        Account account = new Account("Duke", "123");
        Account accountFromDB;
        accountFromDB = this.dbAccounts.add(account);
        assertThat(this.dbAccounts.get(account.getPhone()), is(accountFromDB));
        accountFromDB.setName("Duke1");
        accountFromDB.setPhone("1233");
        assertNull(this.dbAccounts.get(accountFromDB.getPhone()));
        this.dbAccounts.update(accountFromDB);
        account = this.dbAccounts.get(accountFromDB.getPhone());
        assertNotNull(this.dbAccounts.get(accountFromDB.getPhone()));
        assertThat(account, is(accountFromDB));
        this.dbAccounts.delete(accountFromDB.getId());
    }

    @Test
    public void addElementInTheDatabaseCheckElementDeleteAndCheckElementInTheDatabase() {
        Account account = new Account("Duke", "123");
        Account accountFromDB;
        this.dbAccounts.add(account);
        accountFromDB = this.dbAccounts.get(account.getPhone());
        assertNotNull(accountFromDB);
        assertThat(account.getName(), is(accountFromDB.getName()));
        assertThat(account.getPhone(), is(accountFromDB.getPhone()));
        this.dbAccounts.delete(accountFromDB.getId());
        accountFromDB = this.dbAccounts.get(account.getPhone());
        assertNull(accountFromDB);
    }

    @Test
    public void getAllInTheDatabase() {
        Account account = new Account("Duke", "123");
        this.dbAccounts.add(account);
        assertThat(this.dbAccounts.getAll().size(), is(1));
        account.setName("Duke1");
        account.setPhone("123334");
        this.dbAccounts.add(account);
        assertThat(this.dbAccounts.getAll().size(), is(2));
        for (Account ac : this.dbAccounts.getAll().values()) {
            this.dbAccounts.delete(ac.getId());
        }
    }
}
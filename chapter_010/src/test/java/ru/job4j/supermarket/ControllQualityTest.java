package ru.job4j.supermarket;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.supermarket.foods.Milk;
import ru.job4j.supermarket.foods.Water;
import ru.job4j.supermarket.strorage.AbstractStorage;
import ru.job4j.supermarket.strorage.Shop;
import ru.job4j.supermarket.strorage.Trash;
import ru.job4j.supermarket.strorage.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.12.2019.
 */
public class ControllQualityTest {
    private ControllQuality cq;
    private AbstractStorage shop;
    private AbstractStorage trash;
    private AbstractStorage warehouse;

    @Before
    public void start() {
        this.shop = new Shop(new ArrayList<>(Arrays.asList(new Water("Water", LocalDate.now().plusYears(1), LocalDate.now().minusYears(20), 100, 0), new Water("Bread", LocalDate.now().plusDays(7), LocalDate.now(), 100, 0), new Water("Milk", LocalDate.now().plusDays(12), LocalDate.now().plusDays(1), 100, 0))));
        this.warehouse = new Warehouse(new ArrayList<>(Arrays.asList(new Water("Water", LocalDate.now().plusYears(1), LocalDate.now().minusYears(20), 100, 0), new Water("Bread", LocalDate.now().plusDays(7), LocalDate.now().minusDays(1000), 100, 0), new Water("Milk", LocalDate.now().plusDays(12), LocalDate.now().plusDays(1), 100, 0))));
        this.trash = new Trash();
        this.cq = new ControllQuality(new ArrayList<>(Arrays.asList(warehouse, shop, trash)));
    }

    @Test
    public void testAcceptShopShouldTrueAddFoodInShopTestAcceptShouldFalse() {
        ControllQuality controllQuality = new ControllQuality(new ArrayList<>(Arrays.asList(this.shop)));
        assertThat(this.shop.getList().size(), is(3));
        assertTrue(controllQuality.accept(new Water("Water", LocalDate.now().plusYears(1), LocalDate.now().minusYears(20), 100, 0)));
        assertThat(shop.getList().size(), is(4));
        assertFalse(controllQuality.accept(new Water("Water", LocalDate.now().minusDays(30), LocalDate.now().minusYears(1), 100, 0)));
        assertThat(shop.getList().size(), is(4));
    }

    @Test
    public void checkStorageAddFoodCheckStorageResortAndCheckStorageAgain() {
        assertThat(this.shop.getList().size(), is(3));
        assertThat(this.warehouse.getList().size(), is(3));
        assertThat(this.trash.getList().size(), is(0));
        this.shop = new Shop(new ArrayList<>(Arrays.asList(new Milk("milk", LocalDate.now().minusDays(3), LocalDate.now().minusDays(30), 100, 0))));
        this.warehouse = new Warehouse(new ArrayList<>(Arrays.asList(new Milk("milk", LocalDate.now().minusDays(3), LocalDate.now().minusDays(30), 100, 0))));
        this.trash = new Trash(new ArrayList<>(Arrays.asList(new Milk("milk", LocalDate.now().minusDays(3), LocalDate.now().minusDays(30), 100, 0))));
        assertThat(this.shop.getList().size(), is(1));
        assertThat(this.warehouse.getList().size(), is(1));
        assertThat(this.trash.getList().size(), is(1));
        this.cq = new ControllQuality(new ArrayList<>(Arrays.asList(warehouse, shop, trash)));
        this.cq.resort();
        assertThat(this.shop.getList().size(), is(0));
        assertThat(this.warehouse.getList().size(), is(0));
        assertThat(this.trash.getList().size(), is(3));
    }
}
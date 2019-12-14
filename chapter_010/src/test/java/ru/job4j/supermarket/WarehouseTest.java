package ru.job4j.supermarket;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.supermarket.foods.Food;
import ru.job4j.supermarket.foods.Milk;
import ru.job4j.supermarket.strorage.Warehouse;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.11.2019.
 */
public class WarehouseTest {
    private Warehouse warehouse = new Warehouse();
    private LocalDate createDate = LocalDate.of(2000, Month.JANUARY, 30);

    @Test
    public void acceptReturnTrue() {
        Food milk = new Milk("Milk", this.createDate.plusYears(100), this.createDate, 100, 0);
        assertThat(this.warehouse.getList().size(), Is.is(0));
        assertTrue(this.warehouse.accept(milk));
        assertThat(this.warehouse.getList().size(), Is.is(1));
    }

    @Test
    public void acceptReturnFalse() {
        Food milk = new Milk("Milk", LocalDate.now().plusDays(3), this.createDate, 100, 0);
        assertThat(this.warehouse.getList().size(), Is.is(0));
        assertFalse(this.warehouse.accept(milk));
        assertThat(this.warehouse.getList().size(), Is.is(0));
    }
}
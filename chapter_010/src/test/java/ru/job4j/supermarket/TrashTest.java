package ru.job4j.supermarket;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.supermarket.foods.Food;
import ru.job4j.supermarket.foods.Milk;
import ru.job4j.supermarket.strorage.Trash;

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
public class TrashTest {
    private Trash trash = new Trash();
    private LocalDate createDate = LocalDate.of(2000, Month.JANUARY, 30);

    @Test
    public void acceptReturnTrue() {
        Food milk = new Milk("Milk", LocalDate.of(2005, Month.JANUARY, 30), this.createDate, 100, 0);
        assertThat(this.trash.getList().size(), Is.is(0));
        assertTrue(this.trash.accept(milk));
        assertThat(this.trash.getList().size(), Is.is(1));
    }

    @Test
    public void acceptReturnFalse() {
        Food milk = new Milk("Milk", this.createDate.plusYears(30), this.createDate, 100, 0);
        assertThat(this.trash.getList().size(), Is.is(0));
        assertFalse(this.trash.accept(milk));
        assertThat(this.trash.getList().size(), Is.is(0));
    }
}
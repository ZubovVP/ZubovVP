package ru.job4j.supermarket;

import org.junit.Test;
import ru.job4j.supermarket.foods.Bread;
import ru.job4j.supermarket.foods.Food;
import ru.job4j.supermarket.foods.Milk;
import ru.job4j.supermarket.foods.Water;
import ru.job4j.supermarket.strorage.Shop;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 29.11.2019.
 */
public class ShopTest {
    private Shop shop = new Shop();
    private LocalDate createDate = LocalDate.of(2000, Month.JANUARY, 30);

    @Test
    public void acceptReturnTrueAndAddDiscountForFood() {
        Food milk = new Bread("Bread", LocalDate.now().plusDays(1), this.createDate, 100, 0);
        assertThat(this.shop.getList().size(), is(0));
        assertTrue(this.shop.accept(milk));
        assertThat(this.shop.getList().size(), is(1));
        assertThat(this.shop.getList().get(0).getDiscount(), is(35));
    }

    @Test
    public void acceptReturnTrueWithoutDiscountForFood() {
        Food milk = new Milk("Milk", LocalDate.now().plusDays(3000), this.createDate, 100, 0);
        assertThat(this.shop.getList().size(), is(0));
        assertTrue(this.shop.accept(milk));
        assertThat(this.shop.getList().size(), is(1));
        assertThat(this.shop.getList().get(0).getDiscount(), is(0));
    }

    @Test
    public void acceptReturnFalse() {
        Food milk = new Water("Water", this.createDate.plusYears(1), this.createDate, 100, 0);
        assertThat(this.shop.getList().size(), is(0));
        assertFalse(this.shop.accept(milk));
        assertThat(this.shop.getList().size(), is(0));
    }
}
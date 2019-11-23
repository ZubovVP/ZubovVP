package ru.job4j.sort;

import org.junit.Test;
import ru.job4j.foods.Bread;
import ru.job4j.foods.Food;
import ru.job4j.foods.Milk;
import ru.job4j.foods.Water;
import ru.job4j.storage.Shop;
import ru.job4j.storage.Trash;
import ru.job4j.storage.Warehouse;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.11.2019.
 */
public class ControllQualityTest {
    private LocalDate expiryDate = LocalDate.of(2150, Month.JANUARY, 30);
    private LocalDate createDate = LocalDate.of(2000, Month.JANUARY, 30);

    @Test
    public void tesr() {
        ControllQuality cq = new ControllQuality(new Shop(), new Trash(), new Warehouse());
        Food milk = new Milk("Milk", this.expiryDate, this.createDate, 100, 0);
        double result = (checkDate(milk.getCreateDate(), LocalDate.now())) / (checkDate(milk.getCreateDate(), milk.getExpiryDate()));
        assertTrue(result < 0.25);
        cq.control(milk);
        assertThat(cq.getWarehouse().getList().size(), is(1));
        assertThat(cq.getWarehouse().getList().get(0).getName(), is(milk.getName()));
        assertThat(cq.getWarehouse().getList().get(0).getDisscount(), is(0));
    }

    @Test
    public void tesr1() {
        ControllQuality cq = new ControllQuality(new Shop(), new Trash(), new Warehouse());
        Food bread = new Bread("Bread", this.expiryDate.minusYears(100), this.createDate, 100, 0);
        double diferenceDate = (checkDate(bread.getCreateDate(), LocalDate.now())) / (checkDate(bread.getCreateDate(), bread.getExpiryDate()));
        assertTrue(diferenceDate > 0.25 && diferenceDate < 0.75);
        cq.control(bread);
        assertThat(cq.getShop().getList().get(0).getName(), is("Bread"));
        assertThat(cq.getShop().getList().size(), is(1));
        assertThat(cq.getShop().getList().get(0).getName(), is(bread.getName()));
        assertThat(cq.getShop().getList().get(0).getDisscount(), is(0));
    }

    @Test
    public void tesr2() {
        ControllQuality cq = new ControllQuality(new Shop(), new Trash(), new Warehouse());
        Food water = new Water("Water", this.expiryDate.minusYears(130), this.createDate, 100, 0);
        double diferenceDate = (checkDate(water.getCreateDate(), LocalDate.now())) / (checkDate(water.getCreateDate(), water.getExpiryDate()));
        assertTrue(diferenceDate > 0.75);
        cq.control(water);
        assertThat(cq.getShop().getList().get(0).getName(), is("Water"));
        assertThat(cq.getShop().getList().size(), is(1));
        assertThat(cq.getShop().getList().get(0).getName(), is(water.getName()));
        assertTrue(cq.getShop().getList().get(0).getDisscount() > 0);
    }

    @Test
    public void tesr3() {
        ControllQuality cq = new ControllQuality(new Shop(), new Trash(), new Warehouse());
        Food milk = new Milk("Milk", this.expiryDate.minusYears(140), this.createDate, 100, 0);
        cq.control(milk);
        assertThat(cq.getTrash().getList().size(), is(1));
        assertThat(cq.getTrash().getList().get(0).getName(), is(milk.getName()));
    }

    private double checkDate(LocalDate date1, LocalDate date2) {
        Period p = Period.between(date1, date2);
        return (p.getYears() * 365) + (p.getMonths() * 30) + p.getDays();
    }
}
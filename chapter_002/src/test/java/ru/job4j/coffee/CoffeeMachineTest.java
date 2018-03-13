package ru.job4j.coffee;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachineTest {
    @Test
    public void changesTestOne() throws Exception, ImposibleGetCoffee {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(51, 23);
        int[] expected = {10, 10, 5, 2, 1};
        assertArrayEquals(expected, result);
    }
    @Test
    public void changesTestTwo() throws Exception, ImposibleGetCoffee {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(23, 23);
        int[] expected = {};
        assertArrayEquals(expected, result);
    }


}
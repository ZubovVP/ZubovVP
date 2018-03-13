package ru.job4j.coffee;
/**
 * ImposibleGetCoffee.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
public class ImposibleGetCoffee extends Throwable {
    public ImposibleGetCoffee(String description) {
        super(description);
    }
}

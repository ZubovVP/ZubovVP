package ru.job4j.stream.cards;


import java.util.stream.Stream;

import static ru.job4j.stream.cards.Suit.*;
import static ru.job4j.stream.cards.Value.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.01.2021.
 */
public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static void main(String[] args) {
        Suit[] suits = {Diamonds, Hearts, Spades, Clubs};
        Value[] values = {V_6, V_7, V_8};
        Stream.of(suits)
                .flatMap(suit -> Stream.of(values)
                        .map(value -> suit + " " + value))
                .forEach(System.out::println);
    }
}

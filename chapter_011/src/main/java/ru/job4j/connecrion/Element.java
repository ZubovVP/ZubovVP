package ru.job4j.connecrion;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 13.04.2020.
 */
public class Element {
    private final BigInteger first;
    private final BigInteger second;
    private final BigInteger third;

    public Element(BigInteger first, BigInteger second, BigInteger third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public BigInteger getFirst() {
        return first;
    }

    public BigInteger getSecond() {
        return second;
    }

    public BigInteger getThird() {
        return third;
    }

    /*if this.firs number == 0.firstNumber -> true
    if this.second number == 0.secondNumber -> true
    if this.third number == 0.thirdNumber -> true
    * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return (first.longValue() != 0 && first.longValue() == element.first.longValue()) ||
                (second.longValue() != 0 && second.longValue() == element.second.longValue()) ||
                (third.longValue() != 0 && third.longValue() == element.third.longValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    @Override
    public String toString() {
        return "Element{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}

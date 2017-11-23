package ru.job4j.strategy;
/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
	public void draw(Shape shape){
		System.out.println(shape.pic());
	}
}
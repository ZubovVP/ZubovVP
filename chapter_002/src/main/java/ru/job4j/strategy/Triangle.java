package ru.job4j.strategy;
/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Triangle implements Shape{

	public String pic() {
		StringBuilder pic = new StringBuilder();
				pic.append("    +     ");
				pic.append("   + +    ");
				pic.append("  +   +   ");
				pic.append(" +      + ");
				pic.append(" ++++++++ ");
		return pic.toString();
	}
}
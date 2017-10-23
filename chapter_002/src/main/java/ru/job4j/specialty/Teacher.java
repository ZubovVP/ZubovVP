package ru.job4j.specialty;
/**
 * Teacher.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Teacher extends Profession {
	/**
	 * Конструктор.
	 * @param name - имя объекта
	 * @param age - возраст объекта
	 * @param skill - навык
	 */
	public Teacher(String name, int age, String skill){
		super(name, age, skill);
	}
	/**
	 * Метод выводит строку в которой описывается действие между двумя объектами.
	 * @return answer - отправляет результат.
	 */
		public String teach (Profession man){
			String answer = this.name + " " + this.skill + " " + man.getName()  + "а";
			return answer;
		}
}
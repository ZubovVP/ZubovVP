package ru.job4j.specialty;
/**
 * Doctor.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Doctor extends Profession {
	/**
	 * Конструктор.
	 * @param name - имя объекта
	 * @param age - возраст объекта
	 * @param skill - навык
	 */
	public Doctor(String name, int age, String skill) {
		super(name, age, skill);
	}
	/**
	 * Метод который отправляет строку с действием.
	 * @param  man - объект
	 * @return answer - строка с действием
	 */
		public String heal(Profession man) {
			String answer = this.name + " " + this.skill + " " + man.getName() + "a";
			return answer;
		}
}
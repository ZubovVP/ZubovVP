package ru.job4j.specialty;
/**
 * Engineer.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {
	/**
	 * Конструктор.
	 * @param name - имя объекта
	 * @param age - возраст объекта
	 * @param skill - навык
	 */
	public Engineer(String name, int age, String skill){
		super(name, age, skill);
	}
	/**
	 * Метод который отправляет строку с действием.
	 * @param  man - объект
	 * @return answer - строка с действием
	 */
		public String calculate (Profession man){
			String answer = this.name + " делает " + this.skill + " для " + man.getName() + "a";
			return answer;
		}
}
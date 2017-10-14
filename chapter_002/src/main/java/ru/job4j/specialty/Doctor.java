package ru.job4j.specialty;
/**
 * Doctor.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Doctor extends Profession {
		public String heal (Profession man){
			return String say = doc.getName + doc.getSkill() + man.getName() + "a";
		}
}
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
     * Метод наследует парметры класса Profession и позволяет проводить сравнение парметров объекта с парметрами методов.
	 * @return treatmentOfPeople - отправляет результат, может ли данный объект лечить людей.
     */
	public boolean treatmentOfPeople() {
		return (age >= 18 && profileEducation == true) ? true : false;
	}
}
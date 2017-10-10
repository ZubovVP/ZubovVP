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
     * @param age - возраст объекта
	 * @param profileEducation - образование по профилю объекта
	 * @param pasport - наличие паспорта у объекта
	 * @param practicalSkils - наличие практических навыков у объекта
     */
	  Engineer(int age, boolean profileEducation, boolean pasport, boolean practicalSkils) {
		this.age = age;
		this.profileEducation = profileEducation;
		this.pasport = pasport;
		this.practicalSkils = practicalSkils;
	}
	/**
     * Геттер.
	 * @return practicalSkils - позволяет использовать переменную practicalSkils
     */
	public boolean getPracticalSkils() {
		return practicalSkils;
	}
	/**
     * Метод позволяет определить подходит ли объект к эксплуатации оборудования.
	 * @return - отправляет результат.
     */
	public boolean operationOfEquipment() {
	return (age >= 18 && practicalSkils == true) ? true : false;
	}
	/**
     * Метод позволяет определить подходит ли объект к выполнению проектирования.
	 * @return - отправляет результат.
     */
	public boolean design() {
	return (age >= 18 && profileEducation == true && practicalSkils == true) ? true : false;
	}
}
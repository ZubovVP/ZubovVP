package ru.job4j.specialty;
/**
 * Profession.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Profession {
	/**
     * Добавления переменнной age.
     */
	private int age;
	/**
     * Добавления переменнной profileEducation.
     */
	private boolean profileEducation;
	/**
     * Добавления переменнной pasport.
     */
	private boolean pasport;
	/**
     * Добавления переменнной practicalSkils.
     */
	private boolean practicalSkils;
	/**
     * Конструктор.
     * @param age - возраст объекта
	 * @param profileEducation - образование по профилю объекта
	 * @param pasport - наличие паспорта у объекта
     */
	public Profession(int age, boolean profileEducation, boolean pasport) {
		this.age = age;
		this.profileEducation = profileEducation;
		this.pasport = pasport;
	}
	/**
     * Геттер.
	 * @return age - позволяет использовать переменную age
     */
	public int getAge() {
		return this.age;
	}
	/**
     * Геттер.
	 * @return profileEducation - позволяет использовать переменную profileEducation
     */
	public boolean getProfileEducation() {
		return this.profileEducation;
	}
	/**
     * Геттер.
	 * @return pasport - позволяет использовать переменную pasport
     */
	public boolean getPasport() {
		return this.pasport;
	}
	/**
     * Геттер.
	 * @return practicalSkils - позволяет использовать переменную practicalSkils
     */
	public boolean getPracticalSkils() {
		return this.practicalSkils;
	}
	/**
     * Метод позволяет проводит соответствия объекта с требованиями для устройства на работу.
	 * @return  - отправляется результат о соответствия объекта с требованиями.
     */
	public boolean requirementsForWork() {
	return (age >= 18 && pasport == true) ? true : false;
	}
}
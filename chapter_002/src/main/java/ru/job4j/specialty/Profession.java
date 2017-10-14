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
	public int age;
	/**
     * Добавления переменнной name.
     */
	public String name;
	/**
     * Добавления переменнной skill.
     */
	 public String skill;
	/**
     * Конструктор. 
     */
	public Profession(){
		
	}
	/**
     * Конструктор.
	 * @param name - имя объекта
     * @param age - возраст объекта
	 * @param skill - навык
     */
	 public Profession(String name, int age, String skill) {
		this.name = name;
		this.age = age;
		this.skill = skill;
	}
	/**
     * Конструктор.
	 * @param name - имя объекта
     */
	public Profession(String name) {
		this.name = name;
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
	 * @return name - позволяет использовать переменную name
     */
	public String getName() {
		return this.name;
	}
	/**
     * Геттер.
	 * @return skill - позволяет использовать переменную skill
     */
	public String getSkill() {
		return this.skill;
	}
}
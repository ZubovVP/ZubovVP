package ru.job4j.specialty;

/**
* Test.
*
* @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
* @version $Id$
* @since 0.1
*/
class EngineerTest {
	/**
	* Test add.
	*/
	public void whenMakeObjectEngineer() {
		Engineer eng = new Engineer(42, true, true, false);
		boolean resultOne = eng.requirementsForWork();
		boolean expectedOne = true;
		assertThat(resultOne, is(expectedOne));
		boolean resultTwo = eng.operationOfEquipment();
		boolean expectedTwo = false;
		assertThat(resultTwo, is(expectedTwo));
		boolean resultThree = eng.design();
		boolean expectedThree = false;
		assertThat(resultThree, is(expectedThree));
	}
}
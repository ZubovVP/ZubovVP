package ru.job4j.condition;
/**
 * Point.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Point {
	 /**
     * Программа определяет нахождение точки на даннной функции.
     * @param a -  координа А
	 * @param b - координата В
	 * @return is - вывод результата
     */
	  /**
	  *Поле для координат Х.
	  */
   private int x;
     /**
	  *Поле для координат У.
	  */
   private int y;
  /**
     * Метод определяет нахождение точки на даннной функции.
     * @param a -  координа А
	 * @param b - координата В
	 * @return is - вывод результата
     */
   public boolean is(int a, int b) {
	   return y == a * x + b ? true : false;
   }
   /**
     * Метод позволяет позволяет произвести инкапсуляцию данных.
     * @param x -  координа А
	 * @param y - координата В
     */
   public  Point(int x, int y) {
      this.x = x;
      this.y = y;
  }
/**
     * Метод отправляет результа точки А.
	 * @return getX - вывод точки А
     */
  public int getX() {
      return this.x;
  }
 /**
     * Метод отправляет результа точки В.
	 * @return getY - вывод точки В
     */
  public int getY() {
     return this.y;
  }
}
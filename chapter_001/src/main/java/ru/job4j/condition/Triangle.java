package ru.job4j.condition;
/**
 * Triangle.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public class Triangle {
	 /**
     * Программа позволяет вычислять площадь треугольника.
     * @param а - Координаты точки А
	 * @param b - Координаты точки В
	 * @param c - Координаты точки С
	 * @return rsl - вывод площади треугольника
     */
	 /**
	  *Поле для координат А.
	  */
	private Point a;
	/**
	  *Поле для координат В.
	  */
	private Point b;
	/**
	  *Поле для координат С.
	  */
	private Point c;
	 /**
     * Метод принимает координаты точек и происходит инициализиция переменнных с модификатором this.
     * @param a - Координаты точки А
	 * @param b - Координаты точки В
	 * @param c - Координаты точки С
     */
		public Triangle(Point a, Point b, Point c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		 /**
     * Метод позволяет вычислить расстояние между точками.
     * @param left - Координаты точки первой точки
	 * @param right - Координаты точки второй точки
	 * @return distance - вывод расстояние между точками
     */
		public double distance(Point left, Point right) {

			return Math.sqrt((left.getX() - right.getX()) ^ 2 + (left.getY() + right.getY()) ^ 2);
		}
	/**
     * Метод позволяет вычислить расстояние между точками.
     * @param ab - Расстония между точками AB
	 * @param ac - Расстония между точками AC
	 * @param bc - Расстония между точками BC
	 * @return period - вывод периметра треугольника
     */
		public double period(double ab, double ac, double bc) {
			return ((ab + ac + bc) / 2);
		}
	 /**
     * Метод позволяет вычислить площадь треугольника.
	 * @return area - вывод площади треугольника
     */
		public double area() {
    double rsl = -1;
    double ab = this.distance(this.a, this.b);
    double ac = this.distance(this.a, this.c);
    double bc = this.distance(this.b, this.c);
    double p = this.period(ab, ac, bc);
    if (this.exist(ab, ac, bc)) {
        rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
    }
    return rsl;
	}
	/**
     * Метод провести проверку на существование треугольника с задаными координатами.
     * @param ab - Расстония между точками AB
	 * @param ac - Расстония между точками AC
	 * @param bc - Расстония между точками BC
	 * @return exist - вывод результата на сосздание треугольника с задаными координатами
     */
   private boolean exist(double ab, double ac, double bc) {
	   return ab + bc > ac && ab + ac > bc && bc + ac > ab;
	}
}
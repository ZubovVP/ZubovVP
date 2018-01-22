package ru.job4j.chess;
/**
 * Cell.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
class Cell {
   private int x;
   private int y;

     Cell(int x ,int y ){
        this.x = x;
        this.y = y;
    }
    /**
     * Метод позволяет получать координату "Х" определённой шахматной клетке.
     * @return x - координата "Х" шахматной клетки
     */
 int getX(){
    return x;
}
    /**
     * Метод позволяет получать координату "Y" определённой шахматной клетке.
     * @return y - координата "Y" шахматной клетки
     */
 int getY(){
    return y;
}

}

package ru.job4j.chess;
/**
 * Figure.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
  abstract class Figure {
    private final Cell position;

    Figure(Cell position) {
        this.position = position;
    }

    /**
     * Метод позволяет совершить ход фигуре в соответствии с правилами.
     *
     * @param source - объект с координатами начального месторасположения
     * @param dest   - объект с координатами конечного месторасположения
     * @return Cell[] - массив с значением хода фигуры.
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImposibleMoveException;

    /**
     * Метод позволяет создать фигуру.
     *
     * @param dest - объект с координатами создания фигуры на доске
     * @return Figure - созданный объект с заданами координатами
     */
    abstract Figure copy(Cell dest) throws ImposibleCreateFigure;
  /**
   * Метод позволяет передать значение position у объекта
   *
   * @param figure - объект с координатами создания фигуры на доске
   * @return Cell - position
   */
    Cell getPosition(Figure figure) {
        return position;
    }
}
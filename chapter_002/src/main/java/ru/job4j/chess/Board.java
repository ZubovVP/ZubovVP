package ru.job4j.chess;
/**
 * Board.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
class Board {
    /**
     * Создаём шахматную доску
     */
    private Figure[][] figures = new Figure[8][8];
    /**
     * Метод позволяет проверить возможно ли на доске создать фигуру, не занято ли место другой фигурой.
     * @param dest - объект с координатами создания фигуры на доске
     * @return bollean - результат создания
     */
    boolean create(Cell dest) {
         boolean result = false;
           if (this.figures[dest.getX()][dest.getY()] == null){
               result = true;
           }
           return result;
    }
    /**
     * Метод позволяет совершить ход фигуре.
     * @param source - Начальные координаты фигуры
     * @param dest - Конечные координаты фигуры
     * @return bollean - результат создания
     */
    boolean move(Cell source, Cell dest) throws FigureNotFoundException, OccupiedWayException, ImposibleMoveException, ImposibleCreateFigure {
         Cell[] cells;
        //проверяем наличие фигуры на клетке
        if (figures[source.getX()][source.getY()] == null) {
            throw new FigureNotFoundException("Фигура не найдена");
        } else {
            //получаем путь фигуры
            cells = figures[source.getX()][source.getY()].way(source, dest);
            //проверяем что на пути нет других фигур
            for (int x = 1; x < cells.length; x++) {
                if (figures[cells[x].getX()][cells[x].getY()] != null) {
                    throw new OccupiedWayException("Данная клетка занята другой фигурой");
                }
            }
            // добавляем фигуру на доску с новым месторасположением
            figures[dest.getX()][dest.getY()] = figures[source.getX()][source.getY()].copy(dest);
            //удаляем старую фигуру с доски
            figures[cells[0].getX()][cells[0].getY()] = null;
            return true;
        }
    }
    /**
     * Метод позволяет добавить фигуру на доску.
     * @param figure - Объект(фигура)
     * @param source - Координаты фигуры
     */
    void addFigure(Figure figure, Cell source) {
        figures[source.getX()][source.getY()] = figure;
    }
    //Данный метод понадобился только для тестирования программного кода
    Figure[][] getBoard(){
        return figures;
    }
}



package ru.job4j.chess;

import static java.lang.Math.abs;

/**
 * Bishop.
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 20.01.2018
 */
class Bishop extends Figure {

    Bishop(Cell position) {
        super(position);
    }
    /**
     * Метод позволяет совершить ход фигуре в соответствии с правилами.
     * @param source - объект с координатами начального месторасположения
     * @param dest - объект с координатами конечного месторасположения
     * @return Cell[] - массив с значением хода фигуры.
     */
    @Override
    Cell[] way (Cell source, Cell dest) throws ImposibleMoveException {
        int xSource = source.getX();
        int ySource = source.getY();
        int xDest = dest.getX();
        int yDest = dest.getY();
        Cell[] way = new Cell[abs(xDest - xSource) + 1];
        //Проверяем не выходит ли наши координаты за шахматную доску
        if (xDest > 7 || xDest < 0 || xSource > 7 || xSource < 0 || yDest > 7 || yDest < 0 || ySource > 7 || ySource < 0) {
            throw new ImposibleMoveException("Выход за шахматную доску");
        } else if (abs(xDest - xSource) != abs(yDest - ySource)) {
            throw new ImposibleMoveException("Так фигура пойти не может");
        } else {
            for (int step = 0; step < way.length; step++) {
                if (xDest >= xSource && yDest >= ySource) {
                    way[step] = new Cell(xSource, ySource);
                    xSource++;
                    ySource++;
                } else if (xDest <= xSource && yDest <= ySource) {
                    way[step] = new Cell(xSource, ySource);
                    xSource--;
                    ySource--;
                } else if (xDest <= xSource && yDest >= ySource) {
                    way[step] = new Cell(xSource, ySource);
                    xSource--;
                    ySource++;
                } else if (xDest >= xSource && yDest <= ySource) {
                    way[step] = new Cell(xSource, ySource);
                    xSource++;
                    ySource--;
                }
            }
            return way;
        }
    }
    /**
     * Метод позволяет создать фигуру.
     * @param source - объект с координатами создания фигуры на доске
     * @return Figure - созданный объект с заданами координатами
     */
    @Override
    Figure copy (Cell source) throws ImposibleCreateFigure {
        boolean result;
        if(source.getX() > 7 || source.getX() < 0 || source.getY() > 7 || source.getY() < 0){
            throw new ImposibleCreateFigure("Выход за шахматную доску");
        }else{
            result = new Board().create(source);
            if(!result){
                throw new ImposibleCreateFigure("Данная клетка занята другой фигурой");
            }else{
                Bishop bishop = new Bishop(new Cell(source.getX(), source.getY()));
                Board board = new Board();
                //Добавляем фигуру на доску
                board.addFigure(bishop, source);
                return bishop;
            }
        }
    }

}


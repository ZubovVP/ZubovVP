package ru.job4j.array;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.12.2020.
 */
public class MatrixCheck {
    public static boolean monoHorizontal(char[][] board, int row) {
        boolean result = true;
        for (int x = 0; x < board[row].length; x++) {
            if (board[row][x] != 'X') {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean monoVertical(char[][] board, int column) {
        boolean result = true;
        for (int x = 0; x < board.length; x++) {
            if (board[x][column] != 'X') {
                result = false;
                break;
            }
        }
        return result;
    }

    public static char[] extractDiagonal(char[][] board) {
        char[] rsl = new char[board.length];
        for (int x = 0; x < board.length; x++) {
            rsl[x] = board[x][x];
        }
        return rsl;
    }

    public static boolean isWin(char[][] board) {
        boolean result = false;
        for (int x = 0; x < board.length; x++) {
            if ((monoHorizontal(board, x) || monoVertical(board, x))) {
                result = true;
                break;
            }
        }
        return result;
    }
}

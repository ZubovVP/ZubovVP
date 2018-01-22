package ru.job4j.chess;

class ImposibleMoveException extends Exception {
    ImposibleMoveException(String description) {
        super(description);
    }
}

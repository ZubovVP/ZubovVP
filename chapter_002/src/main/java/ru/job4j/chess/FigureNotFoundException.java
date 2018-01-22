package ru.job4j.chess;

class FigureNotFoundException extends Throwable {
    FigureNotFoundException(String description) {
        super(description);
    }
}

package ru.job4j.chess;

    class ImposibleCreateFigure extends Exception {
        ImposibleCreateFigure(String description) {
            super(description);
        }
    }

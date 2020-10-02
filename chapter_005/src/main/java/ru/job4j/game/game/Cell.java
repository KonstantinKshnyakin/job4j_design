package ru.job4j.game.game;

public enum Cell {

    EMPTY(' '), X('X'), O('O');

    Cell(char symbol) {
        this.symbol = symbol;
    }

    private final char symbol;

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}

package ru.job4j.game.game;

public interface IGameField {

    boolean isValidPosition(Position position);

    void setPosition(Position position, Cell cell);

    Cell[][] getField();
}

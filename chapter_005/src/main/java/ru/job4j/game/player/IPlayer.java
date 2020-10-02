package ru.job4j.game.player;

import ru.job4j.game.game.Cell;
import ru.job4j.game.game.Position;

public interface IPlayer {

    Position getMove();

    Cell getCell();

    int getId();
}

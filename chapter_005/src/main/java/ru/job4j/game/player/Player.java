package ru.job4j.game.player;

import ru.job4j.game.game.Cell;
import ru.job4j.game.game.IGameField;
import ru.job4j.game.outer.IInputOutput;

import java.util.Objects;

public abstract class Player implements IPlayer {

    protected static int countGamers = 0;
    protected final Cell cell;
    protected final int id;
    protected final IInputOutput inputOutput;
    protected final IGameField gameField;


    public Player(Cell cell, IGameField gameField, IInputOutput inputOutput) {
        this.id = ++countGamers;
        this.gameField = gameField;
        this.cell = cell;
        this.inputOutput = inputOutput;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }

        Player player = (Player) o;

        if (id != player.id) {
            return false;
        }
        return Objects.equals(cell, player.cell);
    }

    @Override
    public int hashCode() {
        int result = cell != null ? cell.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (inputOutput != null ? inputOutput.hashCode() : 0);
        result = 31 * result + (gameField != null ? gameField.hashCode() : 0);
        return result;
    }
}

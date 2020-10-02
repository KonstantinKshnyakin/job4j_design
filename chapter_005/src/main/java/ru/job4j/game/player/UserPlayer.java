package ru.job4j.game.player;

import ru.job4j.game.game.Cell;
import ru.job4j.game.game.IGameField;
import ru.job4j.game.game.Position;
import ru.job4j.game.outer.IInputOutput;

public class UserPlayer extends Player {

    public UserPlayer(Cell cell, IGameField gameField, IInputOutput inputOutput) {
        super(cell, gameField, inputOutput);
    }

    @Override
    public Position getMove() {
        int size = super.gameField.getField().length;
        int countCell = size * size;
        int answer = super.inputOutput.askAndAnswerInt(String.format("Игрок №%s, введите номер ячейки номер ячейки от 1 до %s: ", id, countCell));
        return convertToPosition(answer);
    }

    private Position convertToPosition(int positionNumber) {
        int size = gameField.getField().length;
        int x = (positionNumber - 1) / size;
        int y = positionNumber - 1 - x * size;
        return new Position(x, y);
    }
}

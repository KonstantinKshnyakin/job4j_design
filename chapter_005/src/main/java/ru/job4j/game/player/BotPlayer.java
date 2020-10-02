package ru.job4j.game.player;

import ru.job4j.game.game.Cell;
import ru.job4j.game.game.IGameField;
import ru.job4j.game.game.Position;
import ru.job4j.game.outer.IInputOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotPlayer extends Player {

    private final Random random;

    public BotPlayer(Cell cell, IGameField gameField, IInputOutput inputOutput) {
        super(cell, gameField, inputOutput);
        random = new Random();
    }

    @Override
    public Position getMove() {
        super.inputOutput.printStr("Ход совершает компьютер.");
        List<Position> emptyCell = getEmptyCell();
        int randomIndex = random.nextInt(emptyCell.size());
        return emptyCell.get(randomIndex);
    }

    private List<Position> getEmptyCell() {
        List<Position> positionList = new ArrayList<>();
        Cell[][] fields = super.gameField.getField();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] == Cell.EMPTY) {
                    positionList.add(new Position(i, j));
                }
            }
        }
        return positionList;
    }
}

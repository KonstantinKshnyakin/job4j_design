package ru.job4j.game.game;

import ru.job4j.game.outer.IInputOutput;
import ru.job4j.game.player.BotPlayer;
import ru.job4j.game.player.IPlayer;
import ru.job4j.game.player.UserPlayer;

import java.util.Arrays;

public class Game {

    private final IInputOutput inputOutput;
    private IGameField gameField;
    private IPlayer player1;
    private IPlayer player2;
    private IPlayer playerNow;

    public Game(IInputOutput inputOutput) {
        this.inputOutput = inputOutput;
    }

    public void initGameField() {
        boolean flag = true;
        while (flag) {
            Integer answer = inputOutput.askAndAnswerInt("Хотите использовать стандартный(т.е 3 на 3) размер поля?"
                    + " Введите : \"1\" - да/ \"0\" - нет");
            if (answer == 0) {
                gameField = new GameField();
                flag = false;
            } else if (answer == 1) {
                Integer size = inputOutput.askAndAnswerInt("Ввиде размер стороны квадрата : ");
                gameField = new GameField(size);
                flag = false;
            }
        }
    }

    public void initialPlayers() {
        boolean flag = true;
        while (flag) {
            int answer = inputOutput.askAndAnswerInt("Сколько игроков будут играть? Ввведите : 1/2");
            if (answer == 2) {
                player1 = new UserPlayer(Cell.X, gameField, inputOutput);
                player2 = new UserPlayer(Cell.O, gameField, inputOutput);
                flag = false;
            } else if (answer == 1) {
                player1 = new UserPlayer(Cell.X, gameField, inputOutput);
                player2 = new BotPlayer(Cell.O, gameField, inputOutput);
                flag = false;
            }
        }
        playerNow = player1;
    }

    public void play() {
        Position position = new Position(-1, -1);
        boolean flag1 = true;
        while (flag1) {
            boolean flag2 = true;
            while (flag2) {
                inputOutput.printObj(gameField);
                position = playerNow.getMove();
                boolean validPosition = gameField.isValidPosition(position);
                flag2 = !validPosition;
            }
            gameField.setPosition(position, playerNow.getCell());
            flag1 = !isWin(playerNow.getCell());
            if (!flag1) {
                inputOutput.printStr(String.format("Победил игрок №%s!", playerNow.getId()));
            }
            playerNow = playerNow.equals(player1) ? player2 : player1;
        }
    }

    public boolean isWin(Cell cell) {
        boolean winHorizontal = isHorizontalWin(cell);
        boolean winVertical = isVerticalWin(cell);
        boolean winDiagonal = isDiagonalWin(cell);
        return winHorizontal || winVertical || winDiagonal;
    }

    private boolean isHorizontalWin(Cell cell) {
        return isLineWin(cell, (cells, i, j) -> cells[i][j]);
    }

    private boolean isVerticalWin(Cell cell) {
        return isLineWin(cell, (cells, i, j) -> cells[j][i]);
    }

    private boolean isDiagonalWin(Cell cell) {
        int length = gameField.getField().length - 1;
        return isLineWin(cell, (cells, i, j) -> cells[j][length - j])
                || isLineWin(cell, (cells, i, j) -> cells[j][j]);
    }

    private boolean isLineWin(Cell cell, LocalFunction function) {
        Cell[][] field = gameField.getField();
        Cell[] actual = new Cell[field.length];
        Cell[] expected = getExpectedArrCell(cell, field.length);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                actual[j] = function.apply(field, i, j);
            }
            boolean equals = Arrays.equals(actual, expected);
            if (equals) {
                return true;
            }
        }
        return false;
    }

    private Cell[] getExpectedArrCell(Cell cell, int size) {
        Cell[] expected = new Cell[size];
        Arrays.fill(expected, cell);
        return expected;
    }
}

interface LocalFunction {

    Cell apply(Cell[][] cells, int i, int j);
}

package ru.job4j.game.game;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class GameField implements IGameField {

    private Cell[][] field;
    private final int defaultSize = 3;

    public GameField(int size) {
        initField(size);
    }

    public GameField() {
        initField(defaultSize);
    }

    private void initField(int size) {
        field = new Cell[size][size];
        for (Cell[] cells : field) {
            Arrays.fill(cells, Cell.EMPTY);
        }
    }

    @Override
    public Cell[][] getField() {
        return field;
    }

    @Override
    public boolean isValidPosition(Position position) {
        Cell cell = field[position.getX()][position.getY()];
        return Cell.EMPTY.equals(cell);
    }

    @Override
    public void setPosition(Position position, Cell cell) {
        field[position.getX()][position.getY()] = cell;
    }

    @Override
    public String toString() {
        String sep = System.lineSeparator();
        StringBuilder builder = new StringBuilder();
        String divider = getDivider();
        builder.append(sep)
                .append(divider)
                .append(sep);
        for (int i = 0; i < field.length; i++) {
            builder.append("| ");
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == Cell.EMPTY) {
                    builder.append(String.format("%2s | ", (field[i].length * i) + j + 1));
                } else {
                    builder.append(String.format("%2s | ", field[i][j]));
                }
            }
            builder.append(sep).append(divider).append(sep);
        }
        return builder.toString();
    }

    private String getDivider() {
        return StringUtils.repeat("-----", field.length);
    }
}

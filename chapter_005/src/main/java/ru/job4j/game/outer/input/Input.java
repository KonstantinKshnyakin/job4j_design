package ru.job4j.game.outer.input;

import java.util.Scanner;

public class Input implements IInput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Integer readInt() {
        return scanner.nextInt();
    }
}

package ru.job4j.game;

import ru.job4j.game.game.Game;
import ru.job4j.game.outer.InputOutput;
import ru.job4j.game.outer.input.Input;
import ru.job4j.game.outer.output.Output;

public class StartGame {

    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();
        InputOutput inputOutput = new InputOutput(input, output);
        Game game = new Game(inputOutput);
        game.initGameField();
        game.initialPlayers();
        game.play();
    }
}

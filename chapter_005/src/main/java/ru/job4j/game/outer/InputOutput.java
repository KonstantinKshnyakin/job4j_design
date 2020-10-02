package ru.job4j.game.outer;

import ru.job4j.game.outer.input.IInput;
import ru.job4j.game.outer.output.IOutput;

public class InputOutput implements IInputOutput {

    private final IInput input;
    private final IOutput output;

    public InputOutput(IInput input, IOutput output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public Integer askAndAnswerInt(String question) {
        output.printStr(question);
        return input.readInt();
    }

    @Override
    public void printStr(String str) {
        output.printStr(str);
    }

    @Override
    public void printObj(Object obj) {
        output.printObj(obj);
    }
}

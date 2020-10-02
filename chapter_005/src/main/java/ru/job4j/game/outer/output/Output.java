package ru.job4j.game.outer.output;

public class Output implements IOutput {

    @Override
    public void printStr(String str) {
        System.out.println(str);
    }

    @Override
    public void printObj(Object obj) {
        System.out.println(obj);
    }
}

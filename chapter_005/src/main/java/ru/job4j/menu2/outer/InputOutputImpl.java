package ru.job4j.menu2.outer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutputImpl implements Output, InputOutput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        printStr(question);
        return read();
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void printStr(String str) {
        System.out.println(str);
    }
}

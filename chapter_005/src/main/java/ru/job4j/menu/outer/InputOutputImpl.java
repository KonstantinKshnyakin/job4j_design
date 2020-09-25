package ru.job4j.menu.outer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutputImpl implements Output, InputOutput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public List<Integer> ask(String question) {
        printStr(question);
        return read();
    }

    @Override
    public List<Integer> read() {
        ArrayList<Integer> listId = new ArrayList<>();
        String line = scanner.nextLine();
        String[] splitId = line.split("\\.");
        for (String id : splitId) {
            int intId = Integer.parseInt(id);
            listId.add(intId);
        }
        return listId;
    }

    @Override
    public void printStr(String str) {
        System.out.println(str);
    }
}

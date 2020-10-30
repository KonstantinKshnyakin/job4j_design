package ru.job4j.spring.di.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {

    private Scanner scanner;

    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> read() {
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            if (str.equals("q")) {
                break;
            }
            list.add(str);
        }
        scanner.close();
        return list;
    }
}

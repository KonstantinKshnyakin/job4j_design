package ru.job4j.spring.di.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    public List<String> read() {
        ArrayList<String> list = new ArrayList<>();
        String str = scanner.nextLine();
        while (!str.isBlank() || !str.isEmpty()) {
            str = scanner.nextLine();
            if (str.equals("q")) {
                break;
            }
            list.add(str);
        }
        scanner.close();
        return list;
    }
}

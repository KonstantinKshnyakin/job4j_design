package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String FINISH = "закончить";
    private static final String STOP = "стоп";
    private static final String PROCEED = "продолжить";
    private static final String OUTPUT = "./chapter_002/src/main/resources/chat.txt";
    private static final String INPUT = "./chapter_002/src/main/resources/answers.txt";
    private final List<String> answers = new ArrayList<>();

    public void startChat() {
        initListAnswers();
        List<String> strList = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {

            String answer;
            boolean isRespond = true;
            String inputLine = scanner.nextLine();

            while (!inputLine.equals(FINISH)) {
                isRespond = !inputLine.equals(STOP) && (inputLine.equals(PROCEED) || isRespond);
                strList.add(inputLine);
                if (isRespond) {
                    answer = getRandomAnswer();
                    System.out.println(answer);
                    strList.add(answer);
                }
                inputLine = scanner.nextLine();
            }
        }
        printToFile(strList);
    }

    private void initListAnswers() {
        try {
            List<String> strings = Files.readAllLines(Paths.get(INPUT));
            answers.addAll(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomAnswer() {
            Random random = new Random();
            int index = random.nextInt(answers.size());
        return answers.get(index);
    }

    private void printToFile(List<String> outputStrList) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(OUTPUT, true)))) {
            outputStrList.forEach(s -> out.write(s + System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.startChat();
    }
}

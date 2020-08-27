package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final List<String> answers = List.of("Более трех часов гремел бой",
            "Без пяти минут профессор", "Селят за 3 минуты без вопросов.", "В четверг торги не проводились."
            , "Круглый стол завершит работу в субботу.", "Их вахта завершится в конце августа.", "Добрый вечер, Где тут у вас ноги вытереть?");

    public void startChat() {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("chat.txt", true)))) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            String inputLine;
            String answer = "";
            boolean isRespond = true;
            while (!(inputLine = scanner.nextLine()).equals("закончить")) {
                isRespond = !inputLine.equals("стоп") && (inputLine.equals("продолжить") || isRespond);
                if (isRespond) {
                    int randomInt = random.nextInt(answers.size());
                    answer = answers.get(randomInt);
                    System.out.println(answer);
                }
                printToFile(out, inputLine, answer, isRespond);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printToFile(PrintWriter out, String inputLine, String answer, boolean isRespond) {
        out.write(inputLine + System.lineSeparator());
        if (isRespond) {
            out.write(answer + System.lineSeparator());
        }
    }

    public static void main(String[] args) {
        final ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.startChat();
    }
}

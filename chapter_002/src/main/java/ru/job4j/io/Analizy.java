package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public static void unavailable(String source, String target) {
        List<String> outputList = readAndProcessing(source);
        write(target, outputList);
    }

    private static List<String> readAndProcessing(String source) {
        final ArrayList<String> outputList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {

            StringBuilder builder = new StringBuilder();
            String readLine = in.readLine();
            boolean isStart = true;

            while (readLine != null) {
                if (!readLine.isBlank()) {
                    String time = readLine.split(" ")[1];
                    if (isStart && isNotAvailable(readLine)) {
                        builder.append("с ").append(time).append(" до ");
                        isStart = false;
                    } else if (!isStart && isAvailable(readLine)) {
                        builder.append(time).append(System.lineSeparator());
                        outputList.add(builder.toString());
                        builder = new StringBuilder();
                        isStart = true;
                    }
                }
               readLine = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputList;
    }

    private static boolean isNotAvailable(String line) {
        return (line.startsWith("500") || line.startsWith("400"));
    }

    private static boolean isAvailable(String line) {
        return (line.startsWith("200") || line.startsWith("300"));
    }

    private static void write(String target, List<String> outputList) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            outputList.forEach(out::write);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "./chapter_002/src/main/resources/server.txt";
        String target = "./chapter_002/src/main/resources/unavailable.txt";
        unavailable(source, target);
    }
}

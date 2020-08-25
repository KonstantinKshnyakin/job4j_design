package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public static void unavailable(String source, String target) {
        List<String> read = read(source);
        List<String> outputList = processing(read);
        write(target, outputList);
    }

    private static List<String> read(String source) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines()
                    .filter(s -> !s.isBlank() || !s.isEmpty())
                    .forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static List<String> processing(List<String> inputList) {
        List<String> outputList = new ArrayList<>();
        String status1 = "500";
        String status2 = "400";
        StringBuilder builder = new StringBuilder();
        for (String str : inputList) {
            String[] split = str.split(" ");
            String element = split[0];
            if (element.equals(status1) || element.equals(status2)) {
                if (element.equals("500") || element.equals("400")) {
                    builder.append("с ").append(split[1]).append(" до ");
                    status1 = "200";
                    status2 = "300";
                } else {
                    builder.append(split[1]).append("\r\n");
                    outputList.add(builder.toString());
                    builder = new StringBuilder();
                    status1 = "500";
                    status2 = "400";
                }
            }
        }
        return outputList;
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

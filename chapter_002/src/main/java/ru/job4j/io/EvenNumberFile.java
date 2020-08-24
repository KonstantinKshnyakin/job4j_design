package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            print(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void print(StringBuilder text) {
        String[] split = text.toString().split("\r\n");
        for (String str : split) {
            int integer = Integer.parseInt(str);
            System.out.printf("%3s - is even? - %s%n", integer, integer % 2 == 0);
        }
    }
}
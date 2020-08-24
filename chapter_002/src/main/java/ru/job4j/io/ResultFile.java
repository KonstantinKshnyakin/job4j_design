package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (BufferedOutputStream bufOutStream = new BufferedOutputStream(new FileOutputStream("result.txt"))) {
            bufOutStream.write(getMultiplicationTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] getMultiplicationTable() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                builder.append(String.format("%s * %s = %s, ", i, j, i * j));
            }
            builder.append("\n");
        }
        return builder.toString().getBytes();
    }
}
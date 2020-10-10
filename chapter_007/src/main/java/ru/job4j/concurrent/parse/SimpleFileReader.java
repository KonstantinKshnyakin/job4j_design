package ru.job4j.concurrent.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SimpleFileReader {

    public static synchronized String readFileUTF8(File file) {
        return readFile(file, StandardCharsets.UTF_8);
    }

    public static synchronized String readFileASCII(File file) {
        return readFile(file, StandardCharsets.US_ASCII);
    }

    private static synchronized String readFile(File file, Charset charset) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file, charset))) {
            while (br.ready()) {
                builder.append(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}

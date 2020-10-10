package ru.job4j.concurrent.parse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileWriter {

    public static synchronized void writeFile(File file, String content) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

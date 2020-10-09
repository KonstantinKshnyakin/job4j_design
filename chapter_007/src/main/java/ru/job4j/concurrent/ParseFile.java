package ru.job4j.concurrent;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ParseFile {

    private volatile File file;

    public void setFile(File f) {
        if (file == null) {
            synchronized (this) {
                if (file == null) {
                    this.file = f;
                }
            }
        }
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                builder.append(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public synchronized String getContentWithoutUnicode() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.US_ASCII))) {
            while (br.ready()) {
                builder.append(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public synchronized void saveContent(String content) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

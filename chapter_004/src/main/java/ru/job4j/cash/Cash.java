package ru.job4j.cash;

import org.apache.log4j.lf5.util.Resource;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cash {

    private final Map<String, SoftReference<String>> map = new HashMap<>();

    public String get(String fileName) {
        String text;
        SoftReference<String> softText = map.get(fileName);
        if (softText != null) {
            text = softText.get();
        } else {
            text = read(fileName);
            map.put(fileName, new SoftReference<>(text));
        }
        return text;
    }

    private String read(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedInputStream buff = new BufferedInputStream(new Resource(fileName).getInputStream())) {
            while (buff.available() > 0) {
                builder.append((char) buff.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}

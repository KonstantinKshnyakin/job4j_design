package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Map<String, String> stringMap = read.lines()
                    .filter(s -> !s.isBlank() || !s.isEmpty())
                    .filter(s -> !s.startsWith("#"))
                    .collect(Collectors.toMap(s -> s.split("=")[0], s -> s.split("=")[1]));
            values.putAll(stringMap);
            System.out.println(values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./chapter_002/src/main/resources/app.properties");
//        System.out.println(config);
        config.load();

    }
}

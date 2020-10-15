package ru.job4j.concurrent.async.in;

import org.apache.log4j.lf5.util.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleFileReader {

    public static final String SOURCE = "source.txt";
    private final BufferedReader reader;

    public SimpleFileReader() {
        reader = new BufferedReader(new Resource(SOURCE).getInputStreamReader());
    }

    public List<String> readFile() {
        List<String> listMembers = new ArrayList<>();
        try (reader) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.contains("first_name")) {
                    line = reader.readLine();
                }
                listMembers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listMembers;
    }
}

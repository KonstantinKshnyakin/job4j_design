package ru.job4j.concurrent.async.out.file;

import ru.job4j.concurrent.async.model.Member;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SimpleFileWriter {

    public static final String EXTENSION = ".txt";

    public void writeToFile(List<Member> membersList) {
        for (Member member : membersList) {
            String fileName = member.getCountry().getName() + EXTENSION;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(member + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

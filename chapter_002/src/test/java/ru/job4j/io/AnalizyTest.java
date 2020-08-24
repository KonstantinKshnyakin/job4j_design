package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private String str1 = "200 10:56:01\n\n500 10:57:01\n\n400 10:58:01\n\n200 10:59:01\n\n500 11:01:02\n\n200 11:02:02";
    private String str2 = "200 10:56:01\n\n200 10:57:01\n\n300 10:58:01\n\n400 10:59:01\n\n500 11:01:02\n"
            + "\n400 11:02:02\n\n400 11:03:02\n\n500 11:04:02\n\n200 11:05:02\n\n200 11:06:02\n\n300 11:07:02\n\n"
            + "200 11:08:02\n\n500 11:09:02\n\n400 11:10:02\n\n200 11:11:02\n\n";

    @Test
    public void when() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println(str1);
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> result = readResult(target.getAbsolutePath());
        assertThat(
                result.get(0),
                is("с 10:57:01 до 10:59:01")
        );
        assertThat(
                result.get(1),
                is("с 11:01:02 до 11:02:02")
        );
    }

    @Test
    public void when2() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println(str2);
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> result = readResult(target.getAbsolutePath());
        System.out.println(result);
        assertThat(
                result.get(0),
                is("с 10:59:01 до 11:05:02")
        );
        assertThat(
                result.get(1),
                is("с 11:09:02 до 11:11:02")
        );
    }

    private static List<String> readResult(String source) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            in.lines()
                    .forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}

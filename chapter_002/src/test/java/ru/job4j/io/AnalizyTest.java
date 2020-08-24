package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void when() {
        String source = "./src/test/resources/analize/server1.txt";
        String target = "./src/test/resources/analize/unavailable1.txt";
        Analizy.unavailable(source, target);
        List<String> result = readResult(target);
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
    public void when2() {
        String source = "./src/test/resources/analize/server2.txt";
        String target = "./src/test/resources/analize/unavailable2.txt";
        Analizy.unavailable(source, target);
        List<String> result = readResult(target);
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

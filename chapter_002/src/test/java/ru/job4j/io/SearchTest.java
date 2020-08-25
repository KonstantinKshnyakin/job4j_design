package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void when() throws IOException {
        File file1 = folder.newFile("source.txt");
        File file2 = folder.newFile("target.txt");
        Path start = Paths.get(file1.getParentFile().getParent());
        List<Path> search = Search.search(start, ".txt");
        assertThat(
                search.contains(file1.toPath()),
                is(true)
        );
        assertThat(
                search.contains(file2.toPath()),
                is(true)
        );
    }
}

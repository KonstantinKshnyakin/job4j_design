package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchDuplicateTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File dir;
    private File file1;
    private File file2;

    @Before
    public void setup() throws IOException {
        dir = new File("./zzz");
        file1 = new File(dir, "z.txt");
        file2 = new File(".", "z.txt");
        dir.delete();
        file1.delete();
        file2.delete();
        dir.mkdir();
        file1.createNewFile();
        file2.createNewFile();
    }

    @Test
    public void whenDuplicateFileThenReturnDuplicate() throws IOException {
        SearchDuplicate searchDuplicate = new SearchDuplicate(".");
        Map<String, List<File>> duplicate = searchDuplicate.getDuplicate();
        System.out.println(duplicate);
        List<File> actual = duplicate.get(file1.getName());
        File actualFile1 = actual.get(0);
        File actualFile2 = actual.get(1);
        assertThat(actualFile1, is(file1));
        assertThat(actualFile2, is(file2));
    }

    @After
    public void cleanup() {
        dir.delete();
        file1.delete();
        file2.delete();
    }
}

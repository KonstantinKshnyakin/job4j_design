package ru.job4j.conrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.SearchFiles;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static ru.job4j.conrol.StrConstant.*;

public class FilesSearch {

    private static final Logger LOG = LoggerFactory.getLogger(FilesSearch.class.getName());
    private final ArgsSearch argsSearch;

    public FilesSearch(ArgsSearch argsSearch) {
        this.argsSearch = argsSearch;
    }

    public static void main(String[] args) {
//        args = new String[] {"-d", "D:\\Work\\projects", "-n", "pom.xml", "-f", "-o", "D:\\Work\\out.txt"};
//        args = new String[] {"-d", "D:\\Work\\projects", "-n", "M.*.xml.*", "-r", "-o", "D:\\Work\\out.txt"};
//        args = new String[]{"-d", "D:\\Work\\projects", "-n", ".xml", "-m", "-o", "D:\\Work\\out.txt"};
        ArgsSearch argsSearch = new ArgsSearch(args);
        FilesSearch filesSearch = new FilesSearch(argsSearch);
        filesSearch.start();
    }

    public void start() {
        List<Path> paths = getListPath();
        printToFile(paths);
    }

    private List<Path> getListPath() {
        Predicate<Path> predicate = getPredicate();
        SearchFiles searcher = new SearchFiles(predicate);
        try {
            Files.walkFileTree(Paths.get(argsSearch.getDirectory()), searcher);
        } catch (IOException e) {
            LOG.error("Error printing to file. ", e);
        }
        return searcher.getPaths();
    }

    private void printToFile(List<Path> paths) {
        try (final PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsSearch.getOutput())))) {
            paths.forEach(path -> out.write(path.toAbsolutePath().toString() + System.lineSeparator()));
        } catch (IOException e) {
            LOG.error("Error printing to file. ", e);
        }
    }

    private Predicate<Path> getPredicate() {
        String searchMethod = argsSearch.getSearchMethod();
        String name = argsSearch.getName();
        return new SearchPredicate(name).getPredicate(searchMethod);
    }
}

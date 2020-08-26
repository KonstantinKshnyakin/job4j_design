package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicate implements FileVisitor<Path> {

    private final Path path;
    private final Map<String, List<File>> duplicateMap;
    private final Map<String, File> filesMap;

    public SearchDuplicate(String path) {
        this.path = Paths.get(path);
        filesMap = new HashMap<>();
        duplicateMap = new HashMap<>();
    }

    public Map<String, List<File>> getDuplicate() throws IOException {
        Files.walkFileTree(this.path, this);
        return duplicateMap;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path files, BasicFileAttributes attrs) {
        File newFile = files.toFile();
        String newFileName = newFile.getName();
        if (filesMap.containsKey(newFileName)) {
            File fileFromMap = filesMap.get(newFileName);
            if (fileFromMap.length() == newFile.length()) {
                duplicateMap.put(newFileName, List.of(newFile, fileFromMap));
            }
        } else {
            filesMap.put(newFileName, newFile);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }
}

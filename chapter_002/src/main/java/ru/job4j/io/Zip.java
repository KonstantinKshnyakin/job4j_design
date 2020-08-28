package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> includeList, ZipOutputStream zos) throws IOException {
        for (Path file : includeList) {
            packSingleFile(file, zos);
        }
    }

    public void packSingleFile(Path source, ZipOutputStream zos) throws IOException {
        final String name = source.toString();
        zos.putNextEntry(new ZipEntry(name));

        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(name))) {
            zos.write(out.readAllBytes());
        }
        zos.closeEntry();
    }

    private List<Path> getIncludeFileList(ArgZip argZip) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> !p.toString().endsWith(argZip.exclude()));
        Files.walkFileTree(Paths.get(argZip.directory()), searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) {

        ArgZip argZip = new ArgZip(args);
        Path inputFile = Paths.get(argZip.directory());
        Path outputFile = Paths.get(argZip.output());

        Zip zip = new Zip();
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile.toString())))) {
            if (inputFile.toFile().isDirectory()) {
                List<Path> includeList = zip.getIncludeFileList(argZip);
                zip.packFiles(includeList, zos);
            } else {
                zip.packSingleFile(inputFile, zos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

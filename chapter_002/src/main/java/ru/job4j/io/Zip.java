package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(File source, Set<Path> excludeSet, ZipOutputStream zos) throws IOException {
        File[] files = source.listFiles();
        for (File file : files) {
            if (!excludeSet.contains(file.toPath())) {
                if (file.isDirectory()) {
                    packFiles(file, excludeSet, zos);
                    continue;
                }
                packSingleFile(file, zos);
            }
        }
    }

    public void packSingleFile(File source, ZipOutputStream zos) throws IOException {
        zos.putNextEntry(new ZipEntry(source.getPath()));

        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
            zos.write(out.readAllBytes());
        }
        zos.closeEntry();
    }

    private HashSet<Path> getExcludeFileSet(ArgZip argZip) throws IOException {
        Path start = Paths.get(argZip.directory());
        List<Path> searchList = Search.search(start, argZip.exclude());
        return new HashSet<>(searchList);
    }

    public static void main(String[] args) {

        ArgZip argZip = new ArgZip(args);
        File inputFile = new File(argZip.directory());
        File outputFile = new File(argZip.output());

        Zip zip = new Zip();
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)))) {
            if (inputFile.isDirectory()) {
                Set<Path> excludeSet = zip.getExcludeFileSet(argZip);
                zip.packFiles(inputFile, excludeSet, zos);
            } else {
                zip.packSingleFile(inputFile, zos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

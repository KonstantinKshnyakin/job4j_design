package ru.job4j.concurrent.parse;

import java.io.File;

public class ParseFile {

    private volatile File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() {
        return SimpleFileReader.readFileUTF8(file);
    }

    public synchronized String getContentWithoutUnicode() {
        return SimpleFileReader.readFileASCII(file);
    }

    public synchronized void saveContent(String content) {
        SimpleFileWriter.writeFile(file, content);
    }
}

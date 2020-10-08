package ru.job4j.concurrent.download;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        args = new String[2];
        args[0] = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        args[1] = "200";
        Callable callable = new Callable(args[0], args[1]);
        callable.download();
    }
}
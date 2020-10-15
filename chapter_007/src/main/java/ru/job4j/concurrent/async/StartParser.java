package ru.job4j.concurrent.async;

import ru.job4j.concurrent.async.in.SimpleFileReader;
import ru.job4j.concurrent.async.in.SimpleProperties;
import ru.job4j.concurrent.async.model.Member;
import ru.job4j.concurrent.async.out.bd.DataBase;
import ru.job4j.concurrent.async.out.file.SimpleFileWriter;
import ru.job4j.concurrent.async.out.server.Server;
import ru.job4j.concurrent.async.util.Parser;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StartParser {

    public static CompletableFuture<Void> saveInDb(List<Member> memberList) {
        return CompletableFuture.runAsync(
                () -> {
                    System.out.println("БД начал работу");
                    SimpleProperties properties = new SimpleProperties();
                    properties.init();
                    DataBase dataBase = new DataBase(properties);
                    dataBase.init();
                    dataBase.saveListMembers(memberList);
                    dataBase.close();
                    System.out.println("БД закончил работу");
                }
        );
    }

    public static CompletableFuture<Void> saveInFile(List<Member> memberList) {
        return CompletableFuture.runAsync(
                () -> {
                    System.out.println("Файл начал работу");
                    SimpleFileWriter writer = new SimpleFileWriter();
                    writer.writeToFile(memberList);
                    System.out.println("Файл закончил работу");
                }
        );
    }

    public static CompletableFuture<Void> saveInServer(List<Member> memberList) {
        return CompletableFuture.runAsync(
                () -> {
                    System.out.println("Сервер начал работу");
                    Server server = new Server();
                    server.printoutForClient(memberList);
                    System.out.println("Сервер закончил работу");
                }
        );
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleFileReader reader = new SimpleFileReader();
        List<String> membersStrList = reader.readFile();
        List<Member> membersList = Parser.parseListMember(membersStrList);

        CompletableFuture.allOf(
                saveInDb(membersList),
                saveInFile(membersList),
                saveInServer(membersList)
        ).get();

        System.out.println("Rонец");
    }
}

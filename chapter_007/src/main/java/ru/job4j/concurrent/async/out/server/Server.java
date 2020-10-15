package ru.job4j.concurrent.async.out.server;

import ru.job4j.concurrent.async.model.Member;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    public static final String OK_200 = "HTTP/1.1 200 OK";

    public void printoutForClient(List<Member> memberList) {
        try (ServerSocket server = new ServerSocket(9000)) {
            Socket socket = server.accept();
            try (OutputStream out = socket.getOutputStream()) {
                StringBuilder builder = new StringBuilder();
                String sep = System.lineSeparator();
                for (Member user : memberList) {
                    builder.append(user).append(sep);
                }
                out.write((OK_200 + sep + sep + builder.toString()).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

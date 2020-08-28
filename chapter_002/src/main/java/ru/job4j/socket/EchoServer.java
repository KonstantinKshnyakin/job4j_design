package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean isBye = false;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!isBye) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        if (str.contains("/?msg=Bye")) {
                            isBye = true;
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write(("HTTP/1.1 200 OK" + System.lineSeparator()).getBytes());
                }
            }
        }
    }
}
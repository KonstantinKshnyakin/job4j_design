package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    boolean isBye = false;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("/?msg=Bye")) {
                            isBye = true;
                        }
                        System.out.println(str);
                    }
                    if (isBye) {
                        out.write(("HTTP/1.1 200 OK - BAY=)" + System.lineSeparator()).getBytes());
                        break;
                    } else {
                        out.write(("HTTP/1.1 200 OK" + System.lineSeparator()).getBytes());
                    }
                }
            }
        }
    }
}
package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static final String HELLO = "Hello";
    public static final String EXIT = "Exit";
    public static final String HELLO_FRIEND = "Hello, dear friend.";
    public static final String OK_200 = "HTTP/1.1 200 OK";

    public static void main(String[] args) throws IOException {
        boolean isBye = false;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!isBye) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String responseStr = "";
                    while (str != null && !str.isEmpty()) {
                        if (str.contains("/?msg=")) {
                            if (str.contains(EXIT)) {
                                isBye = true;
                                responseStr = EXIT;
                            } else if (str.contains(HELLO)) {
                                responseStr = HELLO_FRIEND;
                            } else {
                                responseStr = getResponseStr(str);
                            }
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    final String sep = System.lineSeparator();
                    out.write((OK_200 + sep + sep + responseStr).getBytes());
                }
            }
        }
    }

    private static String getResponseStr(String requestStr) {
        String[] splitReq = requestStr.split(" ");
        String[] splitMsg = splitReq[1].split("=");
        return splitMsg[1];
    }
}
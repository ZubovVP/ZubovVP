package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.01.2021.
 */
public class EchoServer {


    /**
     * For start need in console move C:\Tools\curl-7.74.0_2-win64-mingw\bin> and write curl -i http://localhost:9000/?msg=Hello.
     * For finish need in console write curl -i http://localhost:9000/?msg=Bye.
     *
     * @param args - null.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            String str;
            boolean flag = true;
            while (flag) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    str = in.readLine();
                    while (!str.isEmpty()) {
                        if (str.split(" ")[1].endsWith("Bye")) {
                            flag = false;
                            break;
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}

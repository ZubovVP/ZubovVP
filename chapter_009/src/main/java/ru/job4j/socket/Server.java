package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.09.2019.
 */
public class Server {
    private final Socket socket;

    /**
     * Constructor.
     *
     * @param socket - socket.
     */
    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Start oracle.
     */
    public void start() {
        try (
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                }
            } while (!"exit".equals(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 12312;
        try (final Socket socket = new ServerSocket(port).accept()) {
            new Server(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

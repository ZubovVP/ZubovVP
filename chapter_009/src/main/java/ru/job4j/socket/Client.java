package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 15.09.2019.
 */
public class Client {
    private Socket socket;

    /**
     * Constructor.
     *
     * @param socket - socket.
     */
    public Client(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        int port = 5000;
        String ip = "192.168.0.104";
        try (final Socket socket = new Socket(InetAddress.getByName(ip), port)) {
            new Client(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start application of a client.
     */
    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            out.println("hello");
            String str;
            do {
                str = in.readLine();
                out.println(str);
                System.out.println(str);
            }
            while (!str.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

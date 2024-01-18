package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Server {
    String host = "localhost";
    int port = 8080;

    public void client() throws IOException {
        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println("New connection accepted");

            final String name = in.readLine();

            out.println(String.format("Hi %s, your port is %d", name, socket.getPort()));

            InetAddress inetAddress = InetAddress.getByName(host);
            System.out.println(host + ", ip.address: 127.0.0.1" + inetAddress.getHostAddress());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

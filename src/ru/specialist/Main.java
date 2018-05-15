package ru.specialist;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(30333);

        while (true) {
            Socket soc = socket.accept();
            ClientThread thread = new ClientThread(soc);
            thread.handleConnection();
        }

    }
}

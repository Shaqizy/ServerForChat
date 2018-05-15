package ru.specialist;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {

    private BufferedReader reader;
    private BufferedWriter writer;
    private String str;
    private Socket soc;
    private Thread self;

    public ClientThread(Socket soc) throws IOException {
        this.soc = soc;
        reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
        self = new Thread(this);
    }

    public void handleConnection() {
        System.out.println("Accepted connection from " + soc.getInetAddress().getCanonicalHostName());
        self.start();
    }

    @Override
    public void run() {
        System.out.println("Client thread running...");

        try {
            while (true) {
                str = reader.readLine();
                System.out.println("client > " + str);
                if ("exit".equals(str))
                    break;

                String answer = "accept :" + str + "\n";
                writer.write(answer);
                writer.flush();

            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Client thread quit.");
    }
}

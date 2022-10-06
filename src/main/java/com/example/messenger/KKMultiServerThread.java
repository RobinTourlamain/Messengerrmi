package com.example.messenger;

import java.net.*;
import java.io.*;
import java.util.Set;

public class KKMultiServerThread extends Thread {
    private Socket socket = null;
    public Set<PrintWriter> listWriters;

    public KKMultiServerThread(Socket socket, Set<PrintWriter> listWriters) {
        super("com.example.messenger.KKMultiServerThread");
        this.socket = socket;
        this.listWriters = listWriters;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        ) {
            listWriters.add(out);
            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {
                for (PrintWriter pw : listWriters) {
                    pw.println(inputLine);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

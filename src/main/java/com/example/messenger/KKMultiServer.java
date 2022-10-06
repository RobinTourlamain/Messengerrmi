package com.example.messenger;

import java.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {

        int portNumber = 1234;
        boolean listening = true;

        Set<PrintWriter> listWriters = new HashSet<>();

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new KKMultiServerThread(serverSocket.accept(), listWriters).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
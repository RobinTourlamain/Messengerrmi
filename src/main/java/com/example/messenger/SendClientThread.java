package com.example.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendClientThread extends Thread {
    private Socket socket = null;
    private PrintWriter writer = null;

    public SendClientThread(Socket socket, PrintWriter writer) {
        this.socket = socket;
        this.writer = writer;
    }

    public void run(){
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromUser = null;

            while (true) {
                try {
                    if ((fromUser = stdIn.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer.println(fromUser);
            }
    }

    public void send(String s){

    }
}

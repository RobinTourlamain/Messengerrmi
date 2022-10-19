package com.example.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendClientThread extends Thread {
    private final Chat chat;

    public SendClientThread(Chat chat) {
        this.chat = chat;
    }

    public void run(){
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromUser = null;

        while (true) {
            try {
                if ((fromUser = stdIn.readLine()) == null) break;
                chat.send(fromUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

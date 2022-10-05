package com.example.messenger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SendClientThread extends Thread {
    private Socket socket = null;

    public SendClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromUser;

            while(true){
                while ((fromUser = stdIn.readLine()) != null) {
                    out.println(fromUser);
                }
            }

        }
        catch (Exception ignored) {

        }
    }

    public void send(String s){

    }
}

package com.example.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceiveClientThread extends Thread{
    private Socket socket = null;

    public ReceiveClientThread(Socket socket) {
        this.socket = socket;
    }


    public void run(){
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String fromServer;

            while(true){
                while ((fromServer = in.readLine()) != null) {
                    System.out.println(fromServer);
                }
            }

        } catch (Exception ignored) {

        }
    }

}

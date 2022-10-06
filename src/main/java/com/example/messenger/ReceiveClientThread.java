package com.example.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ReceiveClientThread extends Thread{
    private Socket socket = null;
    private BufferedReader reader = null;

    public ReceiveClientThread(Socket socket, BufferedReader reader) {
        this.socket = socket;
        this.reader = reader;
    }


    public void run(){
            String fromServer = null;
            while (true) {
                try {
                    if ((fromServer = reader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(fromServer);

            }
    }

}

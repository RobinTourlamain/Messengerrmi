package com.example.messenger;/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class MessageClient {

    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;

    public MessageClient(Socket socket){
        try {
//            Socket socket = new Socket("localhost", 1234);
                    this.socket = socket;
                    this.writer = new PrintWriter(socket.getOutputStream(), true);
                    SendClientThread send = new SendClientThread(socket, writer);
                    send.start();
                    this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                    ReceiveClientThread receive = new ReceiveClientThread(socket, reader);                interfered met rcvMessages functie verder
//                    receive.start();
        }
        catch (Exception ignored) {

        }

    }

    public void sendMsgToServer(String msg){
        try{
            writer.write(msg);
            writer.println();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rcvMessagesFromServer(VBox vbox_incoming) {
        new Thread(() -> {
            while(true){
                try{
                    String msg = chat.receive();
                    MessageController.rcvMessage(msg, vbox_incoming);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
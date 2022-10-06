package com.example.messenger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;

public class MessageController {
    public Button Btn_client1;
    public Button Btn_client2;
    public Button Btn_group;
    public Button Btn_send;
    public TextField Txtfld_incoming;
    public TextField Txtfld_outgoing;
    public MessageClient messageClient = new MessageClient();



    @FXML
    public void onGroupButtonClick(){

    }
    public void onClient1ButtonClick(){

    }
    public void onClient2ButtonClick(){

    }
    public void onSendButtonClick(){
        PrintWriter out = messageClient.getWriter();
        out.println(Txtfld_outgoing.getText());
        Txtfld_outgoing.clear();
    }
}
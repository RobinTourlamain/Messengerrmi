package com.example.messenger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {
    public Button Btn_client1;
    public Button Btn_client2;
    public Button Btn_group;
    public Button Btn_send;
    public TextField Txtfld_incoming;
    public TextField Txtfld_outgoing;
    public MessageClient messageClient;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            messageClient = new MessageClient(new Socket("localhost" ,1234));
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageClient.rcvMessagesFromServer(Txtfld_incoming);
    }

    @FXML
    public void onGroupButtonClick(){

    }
    public void onClient1ButtonClick(){

    }
    public void onClient2ButtonClick(){

    }

    @FXML
    public void onSendButtonClick(){
        messageClient.sendMsgToServer(Txtfld_outgoing.getText());
        Txtfld_outgoing.clear();
    }

    public static void rcvMessage(String message, TextField txtfld_incoming){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtfld_incoming.setText(message);
            }
        });
    }


}
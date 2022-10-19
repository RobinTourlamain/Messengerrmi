package com.example.messenger;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Objects;
import java.util.ResourceBundle;

public class MessageController implements Initializable {
    public Button Btn_client1;
    public Button Btn_client2;
    public Button Btn_group;
    public Button Btn_send;
    public TextField Txtfld_incoming;
    public TextField Txtfld_outgoing;
    public MessageClient messageClient;
    public ScrollPane scrlPn_messages;
    public VBox Vbox_messages;
    public static String latest = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Registry myRegistry = LocateRegistry.getRegistry("localhost", 1099);

            Chat chat = (Chat) myRegistry.lookup("ChatService");

            messageClient = new MessageClient(chat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageClient.rcvMessagesFromServer(Vbox_messages);
        Vbox_messages.heightProperty().addListener((observableValue, number, t1) -> scrlPn_messages.setVvalue((double) t1));
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
        String msg = Txtfld_outgoing.getText();
        latest = msg;
        messageClient.sendMsgToServer(msg);
        Txtfld_outgoing.clear();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(5,5,5,10));
        Text text = new Text(msg);
        TextFlow textflow = new TextFlow(text);
        //textflow.setStyle("-fx-color: rgb(239,242,255)");
        //textflow.setStyle("-fx-background-color: rgb(15,125,242)");
        //textflow.setStyle("-fx-background-radius: 20px");
        //textflow.setPadding(new Insets(5,10,5,10));
        //text.setFill(Color.color(0.934,0.945,0.996));
        hbox.getChildren().add(textflow);
        Vbox_messages.getChildren().add(hbox);


    }

    public static void rcvMessage(String message, VBox vbox_incoming){
        if(!Objects.equals(latest, message)){
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setPadding(new Insets(5,5,5,10));
            Text text = new Text(message);
            TextFlow textflow = new TextFlow(text);
            hbox.getChildren().add(textflow);
            Platform.runLater(() -> vbox_incoming.getChildren().add(hbox));
        }
    }


}
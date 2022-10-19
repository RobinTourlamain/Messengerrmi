package com.example.messenger;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat extends Remote {

    void send(String message) throws RemoteException;

    String receive() throws RemoteException, InterruptedException;

}

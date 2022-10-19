package com.example.messenger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatImpl extends UnicastRemoteObject implements Chat {

    private final List<String> messagequeue = new ArrayList<>();
    int cursor = 0;
    int last = 0;


    public ChatImpl() throws RemoteException{}

    @Override
    synchronized public void send(String message) throws RemoteException {
        messagequeue.add(message);
        cursor++;
        notifyAll();
    }

    @Override
    synchronized public String receive() throws RemoteException, InterruptedException {
        last = messagequeue.size();
        while (cursor == last) {
            wait();
        }
        return messagequeue.get(cursor-1);
    }

}

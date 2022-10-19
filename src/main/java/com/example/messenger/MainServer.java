package com.example.messenger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    private void startServer(){
        try{
            // create on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // create a new service named CounterService
            registry.rebind("ChatService", new ChatImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("system is ready");

    }

    public static void main (String[] args){
        MainServer main = new MainServer();
        main.startServer();
    }
}

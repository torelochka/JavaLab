package ru.itis.zheleznov.server;

import ru.itis.zheleznov.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

    private final int port;
    private final ServerSocket serverSocket;
    //TODO потокобезопасный список
    private final List<ClientHandler> clients;

    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server deploy on " + serverSocket.getInetAddress().toString() + " " + port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            Socket client;
            try {
                client = serverSocket.accept();
                System.out.println("New client accept");
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            ClientHandler clientHandler = new ClientHandler(client, this);
            clients.add(clientHandler);
            clientHandler.start();
        }
    }

    public void broadcast(String msg, ClientHandler clientHandler) {
        clients.stream().filter(ClientHandler::isOnline).filter(ch -> ch != clientHandler).forEach(c -> c.sendMsg(msg));
    }
}

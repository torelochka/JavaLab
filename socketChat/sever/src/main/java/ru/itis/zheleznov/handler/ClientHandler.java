package ru.itis.zheleznov.handler;

import ru.itis.zheleznov.server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final Server server;
    private boolean isOnline = true;

    public ClientHandler(Socket client, Server server) {
        this.server = server;
        try {
            this.dataInputStream = new DataInputStream(client.getInputStream());
            this.dataOutputStream = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            isOnline = false;
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = dataInputStream.readUTF();
                broadcast(msg);
                System.out.println("Server: " + msg);

            } catch (IOException e) {
                e.printStackTrace();
                isOnline = false;
                break;
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            dataOutputStream.writeUTF(msg);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void broadcast(String msg) {
        server.broadcast(msg, this);
    }

    public boolean isOnline() {
        return isOnline;
    }
}

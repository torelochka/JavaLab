package ru.itis.zheleznov;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final InetAddress address;
    private final int port;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        try {
            this.socket = new Socket(address, port);
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        readMsg.start();
        writeMsg.start();
    }

    Thread readMsg = new Thread(new Runnable() {
        public void run() {
            while(true) {
                try {
                    String msg = dataInputStream.readUTF();
                    System.out.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    Thread writeMsg = new Thread(new Runnable() {
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while(true) {
                try {
                    dataOutputStream.writeUTF(scanner.nextLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

}

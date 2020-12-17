package ru.itis.zheleznov;

import com.beust.jcommander.JCommander;
import ru.itis.zheleznov.server.Server;
import ru.itis.zheleznov.utils.Args;

public class MainForServer {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        int port = 4242;
        Server server = new Server(port);
        server.start();
    }
}

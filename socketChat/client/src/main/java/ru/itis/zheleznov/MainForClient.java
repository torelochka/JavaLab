package ru.itis.zheleznov;

import com.beust.jcommander.JCommander;
import ru.itis.zheleznov.utils.Args;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainForClient {
    public static void main(String[] argv) throws UnknownHostException {
        /*Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);*/
        Client client = new Client(InetAddress.getByName("127.0.0.1"), 4242);
    }
}

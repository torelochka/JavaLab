package ru.itis.zheleznov.program;

import com.beust.jcommander.JCommander;
import ru.itis.zheleznov.utils.Args;
import ru.itis.zheleznov.utils.Downloader;

public class Main {
    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);

        Downloader downloader = new Downloader(args.mode.equals("one-thread") ? 1 : args.count);
        downloader.downloadImage(args.files, args.folder);
    }
}

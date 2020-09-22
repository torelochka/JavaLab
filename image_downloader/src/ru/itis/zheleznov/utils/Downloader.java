package ru.itis.zheleznov.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;

public class Downloader {

    private int threadsCount;

    public Downloader(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    public void downloadImage(List<String> urls, String folder) {
        ThreadPool threadPool = new ThreadPool(threadsCount);
        for (String url : urls) {
            threadPool.submit(() -> {
                try {
                    BufferedImage img = ImageIO.read(new URL(url));
                    String filename = url.split("/")[url.split("/").length - 1];
                    if (filename.length() > 7) {
                        filename = filename.substring(filename.length() - 10);
                    }
                    File file = new File(folder + "/" + filename);

                    if (!file.exists()) {
                        if (file.createNewFile())
                            System.out.println("File " + filename + " download successfully!");
                    } else {
                        System.out.println("File " + filename + " already exist");

                    }

                    ImageIO.write(img, "jpg", file);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            });
        }
    }
}
package ru.itis.zheleznov.threadPool;

public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2);

        Runnable task1 = (() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " A");
            }
        });
        Runnable task2 = (() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " B");
            }
        });
        Runnable task3 = (() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " C");
            }
        });

        threadPool.submit(task1);
        threadPool.submit(task2);
        threadPool.submit(task3);
    }
}

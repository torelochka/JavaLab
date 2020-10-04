package ru.itis.zheleznov.threadPool;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool2 {
    private Deque<Runnable> tasks;

    private ThreadWorker[] pool;

    public ThreadPool2(int threadsCount) {
        tasks = new ConcurrentLinkedDeque<>();
        pool = new ThreadWorker[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            pool[i] = new ThreadWorker();
            pool[i].start();
        }
    }

    public void submit(Runnable task) {
        tasks.add(task);
    }

    private class ThreadWorker extends Thread {

        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (tasks) {
                    task = tasks.poll();
                }
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}


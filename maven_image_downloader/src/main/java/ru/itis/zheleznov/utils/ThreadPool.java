package ru.itis.zheleznov.utils;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {


    private Deque<Runnable> tasks;

    private ThreadWorker[] pool;

    public ThreadPool(int threadsCount) {
        tasks = new ConcurrentLinkedDeque<>();
        pool = new ThreadWorker[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            pool[i] = new ThreadWorker();
            pool[i].start();
        }
    }

    public void submit(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    public void shutdown() {
        if (tasks.isEmpty()) {
            for (ThreadWorker thread : pool) {
                thread.interrupt();
            }
        } else {
            shutdown();
        }
    }

    private class ThreadWorker extends Thread {

        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalThreadStateException();
                        }
                    }

                    task = tasks.poll();
                }

                task.run();
            }
        }
    }
}


package ru.sbt.jschool.session7.HomeJob;


import java.util.concurrent.atomic.AtomicInteger;

public class Problem1 implements Runnable{

    private static AtomicInteger count = new AtomicInteger();

    private static int countThreads;

    public static void main(String[] args) {
        countThreads = 50;
        new Thread(new Problem1()).start();
    }

    @Override public void run() {
        Thread.currentThread().setName("Thread-" + (count.get()+1));
        Thread thread = new Thread(new Problem1());
        if (count.get()<countThreads-1){
            try {
                count.incrementAndGet();
                thread.start();
                thread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hello from Thread - " + (Thread.currentThread().getName()));
    }
}

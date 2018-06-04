package ru.sbt.jschool.session7.HomeJob;

import java.util.concurrent.atomic.AtomicInteger;

public class Problem2 implements Runnable {

    private static AtomicInteger count = new AtomicInteger();

    private static int threadsCount;

    public static void main(String[] args) {
        threadsCount = 4;
        for (int i = 0; i < threadsCount; i++) {
            new Thread(new Problem2()).start();
        }
    }

    @Override public void run() {
        long ID = Thread.currentThread().getId();
        while(true){
            if (ID==(count.get()+10)) {
                System.out.println("Поток " + (count.get()+1));
                count.incrementAndGet();
            }
            if (count.get()==threadsCount)
                count.set(0);
        }
    }
}

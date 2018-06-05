package ru.sbt.jschool.session7.HomeJob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem2 implements Runnable {

    private static AtomicInteger count = new AtomicInteger();

    private static int threadsCount;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            threadsCount = Integer.parseInt(reader.readLine());
            for (int i = 0; i < threadsCount; i++) {
                new Thread(new Problem2()).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
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

package ru.sbt.jschool.session7.HomeJob;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem5 implements Runnable{

    private static File[] files;

    static {
        File[] list = new File("/Users/amayakbulgadaryan/Documents/Files_for_session-7").listFiles();
        files = new File[list.length-1];
        for (int i = 0; i < files.length; i++)
            files[i] = list[i+1];
    }

    private static int part;

    private static int remainder;

    private static int threadsCount;

    private static AtomicInteger count = new AtomicInteger();

    private static Queue<String> queue = new ArrayBlockingQueue<>(10);

    private void readFile(int localCount){
        int first = localCount*part;
        int remainder = 0;
        if (localCount==(threadsCount-1))
            remainder = Problem5.remainder;
        for (int i = first; i < first+part+remainder; i++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(files[i]));
                StringBuilder stringBuilder = new StringBuilder("");
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                queue.add(stringBuilder.toString()+i);
                new Thread(new Problem5()).start();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int length = files.length;
            System.out.println("Введите количество потоков для чтения файлов:");
        try {
            while (threadsCount<1||threadsCount>length) {
                System.out.println("Количество потоков должно быть в диапозоне 1 - "+length);
                threadsCount = Integer.parseInt(reader.readLine());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            part = length / threadsCount;
            remainder = length % threadsCount;
            for (int i = 0; i < threadsCount; i++)
                new Thread(new Problem5()).start();
    }

    @Override public void run() {
            int localCount = count.get();
            count.incrementAndGet();
            if (count.get()>threadsCount) {
                String result = queue.poll();
                System.out.println(result.split(" ").length+1);
                return;
            }
            while (count.get()<threadsCount);
            readFile(localCount);
    }
}

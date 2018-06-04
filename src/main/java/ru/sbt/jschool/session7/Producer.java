package ru.sbt.jschool.session7;

/**
 */
public class Producer implements Runnable {
    public static final int JOB_PRODUCE_TIME = 500;

    private final JobsStore store;

    private int i = 0;

    public Producer(JobsStore store) {
        this.store = store;
    }

    @Override public void run() {
        try {
            while (true) {
                if (store.cnt < JobsStore.JOB_STORE_SIZE)
                    generateJob();
                else
                    System.out.println("Skip generating jobs! Consumer is bad!");

                Thread.sleep(JOB_PRODUCE_TIME);
            }
        }
        catch (InterruptedException e) {
            System.out.println("e = " + e);
        }
    }

    private void generateJob() {
        //TODO: здесь нужно сгенерировать новое задание!
        synchronized (store) {
            store.store[store.cnt] = new Job(i);
            store.cnt++;
            i++;
            store.notifyAll();
        }
    }
}

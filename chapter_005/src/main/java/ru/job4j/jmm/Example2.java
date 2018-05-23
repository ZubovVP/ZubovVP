package ru.job4j.jmm;

public class Example2 {
    private int count;

//    public synchronized void increment() {
//        this.count++;
//    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int x = 0; x < 1000; x++) {
                    count++;
                    //increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 1000; x++) {
                    count++;
                    //increment();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {
        Example2 test = new Example2();
        test.doWork();
    }
}

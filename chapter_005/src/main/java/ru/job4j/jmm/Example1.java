package ru.job4j.jmm;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        MyThread test1 = new MyThread();
        test1.start();
        Thread.sleep(2000);
        test1.shutdown();
    }
}

class MyThread extends Thread {
    volatile boolean result = true;
    int count;

    public void run() {
        while (result) {
            System.out.println(count++);
        }
    }

    public void shutdown() {
        result = false;
    }
}

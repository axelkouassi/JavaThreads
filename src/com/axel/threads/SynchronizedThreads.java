package com.axel.threads;

public class SynchronizedThreads {

    public static StringBuffer result = new StringBuffer("");

    public static void main(String[] args){

        //Thread
        Thread t1 = new Thread(new ThreadA());
        t1.setName("Thread 1");
        Thread t2 = new Thread(new ThreadB());
        t2.setName("Thread 2");
        Thread t3 = new Thread(new ThreadC());
        t3.setName("Thread 3");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();

        //System.out.println();
        //System.out.println("Final value of result: " + result);
    }
}

//This thread A adds A to StringBuilder result 12 times
class ThreadA implements Runnable{
    @Override
    public void run() {
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 11; i++){
            SynchronizedThreads.result.append("A");
            System.out.println(Thread.currentThread().getName() + ": " + SynchronizedThreads.result);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//This thread B adds B to StringBuilder result 12 times
class ThreadB implements Runnable{
    @Override
    public void run() {
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 11; i++){
            SynchronizedThreads.result.append("B");
            System.out.println(Thread.currentThread().getName() + ": " + SynchronizedThreads.result);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//This thread C adds B to StringBuilder result 12 times
class ThreadC implements Runnable{
    @Override
    public void run() {
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 11; i++){
            SynchronizedThreads.result.append("C");
            System.out.println(Thread.currentThread().getName() + ": " + SynchronizedThreads.result);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


package com.axel.threads;

public class SynchronizedThreads {

    public static void main(String[] args){
        //Thread
        Thread t1 = new Thread(new ThreadA());
        t1.setName("Thread 1");
        Thread t2 = new Thread(new ThreadB());
        t2.setName("Thread 2");
        Thread t3 = new Thread(new ThreadC());
        t3.setName("Thread 3");
        t1.start();
        t2.start();
        t3.start();
    }
}

//This thread A prints A 12 times
class ThreadA implements Runnable{
    @Override
    public void run() {
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 11; i++){
            System.out.println(Thread.currentThread().getName() + ": A");
        }
    }
}

//This thread B prints B 12 times
class ThreadB implements Runnable{
    @Override
    public void run() {
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 11; i++){
            System.out.println(Thread.currentThread().getName() + ": B");
        }
    }
}

//This thread C prints C 12 times
class ThreadC implements Runnable{
    @Override
    public void run() {
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 11; i++){
            System.out.println(Thread.currentThread().getName() + ": C");
        }
    }
}


package com.axel.threads;

public class SynchronizedThreads {

    volatile int status = 1; // used to switch between threads

    public static void main(String[] args){

        SynchronizedThreads instance = new SynchronizedThreads();

        //Creation of three threads
        Thread t1 = new ThreadA(instance);
        Thread t2 = new ThreadB(instance);
        Thread t3 = new ThreadC(instance);

        //Setting names of the three threads
        t1.setName("Thread 1");
        t2.setName("Thread 2");
        t3.setName("Thread 3");

        //Starts the three threads
        t1.start();
        t2.start();
        t3.start();
    }
}

//This thread A prints A 12 times
class ThreadA extends Thread {
    SynchronizedThreads instance;

    ThreadA(SynchronizedThreads instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        try {
            synchronized (instance) {
                for (int i = 0; i < 12; i++) {
                    while (instance.status != 1) {
                        instance.wait();
                    }
                    System.out.print("A");
                    instance.status = 2;
                    instance.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//This thread B prints B 12 times
class ThreadB extends Thread {
    SynchronizedThreads instance;

    ThreadB(SynchronizedThreads instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        try {
            synchronized (instance) {
                for (int i = 0; i < 12; i++) {
                    while (instance.status != 2) {
                        instance.wait();
                    }
                    System.out.print("B");
                    instance.status = 3;
                    instance.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//This thread C prints C 12 times
class ThreadC extends Thread {
    SynchronizedThreads instance;

    ThreadC(SynchronizedThreads instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        try {
            synchronized (instance) {
                for (int i = 0; i < 12; i++) {
                    while (instance.status != 3) {
                        instance.wait();
                    }
                    System.out.print("C");
                    instance.status = 1;
                    instance.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.axel.threads;

public class SynchronizedThreads {

    public static void main(String[] args){

        //Thread
        Thread t1 = new Thread(new Threads());
        Thread t2 = new Thread(new Threads());
        Thread t3 = new Thread(new Threads());
        t1.start();
        t2.start();
        t3.start();

        //three threads
        /*for(int i = 0; i < 2; i++){
            thread.start();;
            inst.print++;
        }*/


    }
}

class Threads implements Runnable{

    public char print = 'A';


    @Override
    public void run() {

        //synchronized (Threads.class){
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 11; i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
               /*try {
                   Threads.class.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }*/
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Threads.class.notifyAll();
        //}
    }
}


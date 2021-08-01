package com.axel.producerconsumer;

import com.axel.practice.ThreadExample;

import java.util.LinkedList;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        // Object of a class that has both produce()and consume() methods
        final ProdConsum pc = new ProdConsum();

        // Create producer thread
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    pc.produce();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    pc.consume();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Set threads name
        producer.setName("Producer");
        consumer.setName("Consumer");

        // Start both threads
        producer.start();
        consumer.start();

        // t1 finishes before t2
        producer.join();
        consumer.join();
    }

    // This class has a list, producer (adds items to list and consumer (removes items).
    public static class ProdConsum {

        // Create a list shared by producer and consumer
        // size of list is randomly chosen
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = (int) (Math.random()*10)+1;

        // Function called by producer thread
        public void produce() throws InterruptedException
        {
            int value = (int) (Math.random()*10)+1;
            System.out.println("List capacity : " + capacity);
            while (true) {
                synchronized (this)
                {
                    // producer thread waits while list is full
                    while (list.size() == capacity)
                        wait();

                    System.out.println(Thread.currentThread().getName() + " produced: " + value);

                    // to insert the jobs in the list
                    list.add(value++);
                    System.out.println("List contents after production: " + list.toString());
                    System.out.println();

                    // notifies the consumer thread that
                    // now it can start consuming
                    notify();

                    // makes the working of program easier
                    // to understand
                    Thread.sleep(1000);
                }
            }
        }

        // Function called by consumer thread
        public void consume() throws InterruptedException
        {
            while (true) {
                synchronized (this)
                {
                    // consumer thread waits while list
                    // is empty
                    while (list.size() == 0)
                        wait();

                    // to retrieve the first job in the list
                    int val = list.removeFirst();

                    System.out.println(Thread.currentThread().getName() + " consumed: " + val);
                    System.out.println("List contents after consumption: " + list.toString());
                    System.out.println();

                    // Wake up producer thread
                    notify();

                    // and sleep
                    Thread.sleep(1000);
                }
            }
        }
    }
}




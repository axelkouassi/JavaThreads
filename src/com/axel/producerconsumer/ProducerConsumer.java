package com.axel.producerconsumer;

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

        // producer finishes before consumer
        producer.join();
        consumer.join();
    }

    // This class has a list, producer (adds items to list) and consumer (removes items).
    public static class ProdConsum {

        // List shared by producer and consumer
        LinkedList<Integer> list = new LinkedList<>();
        // size of list is randomly chosen
        int capacity = (int) (Math.random()*10)+1;

        // Function called by producer thread
        public void produce() throws InterruptedException
        {
            int value = (int) (Math.random()*10)+1;
            System.out.println("List capacity : " + capacity);
            System.out.println();
            while (true) {
                synchronized (this)
                {
                    // producer thread waits while list is full
                    while (list.size() == capacity)
                        wait();

                    System.out.println(Thread.currentThread().getName() + " produced: " + value);

                    // to insert the random number in the list
                    list.add(value);
                    System.out.println("List contents after production: " + list.toString());
                    System.out.println();

                    //Generate random value and assign to value
                    value = (int) (Math.random()*10)+1;



                    // notifies the consumer thread that now it can start consuming
                    notify();

                    // Wait for a second before next instruction
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
                    // consumer thread waits while list is empty
                    while (list.size() == 0)
                        wait();

                    // Retrieve the first random number in the list
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




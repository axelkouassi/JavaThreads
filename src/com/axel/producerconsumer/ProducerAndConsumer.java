package com.axel.producerconsumer;

import java.util.LinkedList;

public class ProducerAndConsumer {

    public static void main(String[] args) throws InterruptedException {

        // Initially, there needs to be some data in order to consume the data.
        // So,Producer object is created first
        Producer p = new Producer();

        // Sending this producer object into the consumer
        Consumer c = new Consumer(p);

        // Create producer thread and consumer thread
        Thread producer = new Thread(p);
        Thread consumer = new Thread(c);

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

    // Producer class extends thread
    // This class has a list, producer (adds items to list) and consumer (removes items).
    public static class Producer extends Thread{

        // List shared by producer and consumer
        LinkedList<Integer> list;
        // size of list is randomly chosen
        int capacity = (int) (Math.random()*10)+1;

        Producer(){

            list = new LinkedList<>();
        }

        @Override
        public void run(){
            produce();
        }

        // Function called by producer thread
        public void produce(){
            int value = (int) (Math.random()*10)+1;
            System.out.println("List capacity : " + capacity);
            System.out.println();
            while (true) {
                synchronized (list) {
                    // producer thread waits while list is full
                    while (list.size() == capacity) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + " produced: " + value);

                    // Insert the random number in the list
                    list.add(value);
                    System.out.println("List contents after production: " + list.toString());
                    System.out.println();

                    //Generate random value and assign to value
                    value = (int) (Math.random()*10)+1;

                    // notifies the consumer thread that now it can start consuming
                    list.notify();

                    // Wait for a second before next instruction
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static class Consumer extends Thread{

        //Creating the object of the producer class
        Producer producer;

        //Assigning the object of the producer class
        Consumer(Producer temp){
            producer = temp;
        }

        @Override
        public void run(){
            consume();
        }

        // Function called by consumer thread
        public void consume()
        {
            while (true) {
                synchronized (producer.list)
                {
                    // consumer thread waits while list is empty
                    while (producer.list.size() == 0) {
                        try {
                            producer.list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Retrieve the first random number in the list
                    int val = producer.list.removeFirst();

                    System.out.println(Thread.currentThread().getName() + " consumed: " + val);
                    System.out.println("List contents after consumption: " + producer.list.toString());
                    System.out.println();

                    // Wake up producer thread
                    producer.list.notify();

                    // and sleep
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

}

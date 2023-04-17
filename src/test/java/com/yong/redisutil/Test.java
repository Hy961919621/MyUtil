package com.yong.redisutil;


import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {

    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        // 生产者线程
        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                queue.offer(i); // 生产数据
                System.out.println("Produced: " + i);
            }
        });

        // 消费者线程
        Thread consumerThread = new Thread(() -> {
            while (true) {
                Integer value = queue.poll(); // 消费数据
                if (value == null) {
                    // 队列为空，休眠一段时间后再次尝试
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                System.out.println("Consumed: " + value);
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}



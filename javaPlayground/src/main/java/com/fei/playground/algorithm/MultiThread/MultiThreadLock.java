package com.fei.playground.algorithm.MultiThread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Volatile
 * ReentrantLock
 * synchronized
 * AtomicInteger
 */
public class MultiThreadLock {

    public int inc = 0;
    //AtomicInteger
    public AtomicInteger atoInc = new AtomicInteger();
    //LOCK
    Lock lock = new ReentrantLock();

    //synchronized
    public synchronized void syncIncrease() {
        inc++;
    }

    public void atomicIncrease() {
        atoInc.getAndIncrement();
    }

    public void lockIncrease() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MultiThreadLock test = new MultiThreadLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
//                    test.syncIncrease();
//                    test.lockIncrease();
//                    System.out.println(String.format("inc now is: %s", test.inc));

                    test.atomicIncrease();
                    System.out.println(test.atoInc);
                }
            }).start();
        }
    }

}

package com.fei.playground.algorithm.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.cn/problems/print-in-order/
 * 三个不同的线程 A、B、C 将会共用一个Foo实例。
 *
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用second() 方法
 * 线程 C 将会调用 third() 方法
 *
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 */
public class E_MultiThread_PrintOrder_1114 {

    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first".
        printFirst.run();
        // mark the first job as done, by increasing its count.
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
            // waiting for the first job to be done.
        }
        // printSecond.run() outputs "second".
        printSecond.run();
        // mark the second as done, by increasing its count.
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
            // waiting for the second job to be done.
        }
        // printThird.run() outputs "third".
        printThird.run();
    }

}

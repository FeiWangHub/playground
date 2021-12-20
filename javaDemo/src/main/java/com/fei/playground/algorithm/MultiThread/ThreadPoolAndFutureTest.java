package com.fei.playground.algorithm.MultiThread;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class ThreadPoolAndFutureTest {

    public static void main(String[] args) {
//        testFuture();
        testCompletableFuture();
    }

    public static void testFuture(){
        System.out.println("main函数开始执行");

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                System.out.println("===task start===");
                Thread.sleep(5000);
                System.out.println("===task finish===");
                return 3;
            }
        });
        //这里需要返回值时会阻塞主线程，如果不需要返回值使用是OK的。倒也还能接收
        //Integer result=future.get();
        System.out.println("main函数执行结束");

//        System.in.read();
    }

    //使用原生的CompletableFuture实现异步操作，加上对lambda的支持
    public static void testCompletableFuture(){
        System.out.println("main函数开始执行");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("===task start===");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===task finish===");
                return 3;
            }
        }, executor);
        future.thenAccept(e -> System.out.println(e));
        System.out.println("main函数执行结束");
    }

    //线程池 有返还调用 begin
    static class CallableImpl implements Callable<Integer> {

        private static Integer value = 0;

        @Override
        public Integer call() throws Exception {
            System.out.println("执行call方法前 value = " + value);
            value = value + 1;
            System.out.println("执行call方法之后 value = " + value);
            return value;
        }
    }

    public void testTreadPool() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor exe = new ThreadPoolExecutor(5, 10, 200,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(20));
        Future task;
        for (int i = 0; i < 5; i++) {
            task = exe.submit(new CallableImpl());
            System.out.println("线程返还结果" + task.get());
        }
        exe.shutdown();
    }
    //线程池 end
}

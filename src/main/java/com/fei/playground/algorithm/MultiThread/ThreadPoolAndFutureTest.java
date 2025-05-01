package com.fei.playground.algorithm.MultiThread;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Future没有返回值
 * CompletableFuture有返回值
 * (thenApplyAsync串行化处理另一个CompletableFuture)
 * (anyOf()和allOf()用于并行化多个CompletableFuture)
 * <p>
 * 廖雪峰教程 https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650
 */
public class ThreadPoolAndFutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        testFuture();
//        testCompletableFuture();
        testSimpleCompletableFuture();
    }

    //串行执行2个sync测试
    public static void testSimpleCompletableFuture() throws InterruptedException, ExecutionException {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });

        cfQuery.thenAccept((e) -> System.out.println("e:" + e));
//        cfQuery.get();

        // cfQuery成功后继续执行下一个任务: (thenApplyAsync串行化处理另一个CompletableFuture)
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(code);
        });
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });

        cfFetch.get();
        //System.out.println(cfQuery.get());
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    public static void testFuture() throws ExecutionException, InterruptedException {
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
        Integer result = future.get();
        System.out.println("main函数执行结束");

//        System.in.read();
    }

    //使用原生的CompletableFuture实现异步操作，加上对lambda的支持
    public static void testCompletableFuture() {
        System.out.println("main函数开始执行");
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //指定要异步执行的函数：get()
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
        //如果执行成功，会执行下边的thenAccept
        future.thenAccept(e -> System.out.println(e));
        //如果执行失败
        future.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
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

package com.fei.playground.algorithm.MultiThread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步编程的7种方式 https://blog.csdn.net/m0_73493267/article/details/126611222
 */
@Slf4j
public class AnsyncProgramming {

    /**
     * 1 线程池测试
     * 将业务逻辑封装到 Runnable 或 Callable 中，交由 线程池 来执行
     */
    @Bean(name = "executorService")
    public ExecutorService downloadExecutorService() {
        return new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000),
                new ThreadFactoryBuilder().setNamePrefix("defaultExecutorService-%d").build(),
                (r, executor) -> log.error("defaultExecutor pool is full! "));
    }

    /**
     * 2 Future
     * Java 从1.5版本开始，提供了 Callable 和 Future，可以在任务执行完毕之后得到任务执行结果/取消任务/查询任务是否完成等
     * cancel()：取消任务，如果取消任务成功返回true，如果取消任务失败则返回false
     * isCancelled()：表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true
     * isDone()：表示任务是否已经完成，如果完成，返回true
     * get()：获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
     * get(long timeout, TimeUnit unit)：用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null
     */



}

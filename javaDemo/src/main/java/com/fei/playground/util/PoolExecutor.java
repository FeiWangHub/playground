package com.fei.playground.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuansq
 * @Description 线程池
 * @create 2021-05-12 20:26
 */
public class PoolExecutor {
    /**
     * 构造一个固定线程数目的线程池，配置的corePoolSize与maximumPoolSize大小相同，
     * 同时使用了一个无界LinkedBlockingQueue存放阻塞任务，
     * 因此多余的任务将存在再阻塞队列，不会由RejectedExecutionHandler处理
     *
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(7,  7, 0L,
            TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<>());


    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}

package com.fei.playground.algorithm.MultiThread;

public class ThreadPools {

    /**
     * https://engineering.zalando.com/posts/2019/04/how-to-set-an-ideal-thread-pool-size.html
     * Number of threads = Number of Available Cores * (1 + Wait time / Service time)
     * Wait time / Service time - this ratio is often called blocking coefficient.
     * (如果需要考虑CPU的占用度 可以用Number of threads = Number of Available Cores * Target CPU utilization * (1 + Wait time / Service time))
     * (每秒吞吐量计算 = 核心数 / 每个request的耗时，比如10核心，每个请求耗时0.1秒，每秒吞吐量是100个请求)
     * @param waitTime is the time spent waiting for IO bound tasks to complete, say waiting for HTTP response from remote service
     * @param serviceTime is the time spent being busy, say processing the HTTP response, marshaling/unmarshaling, any other transformations etc.
     * @return best number of threads
     */
    public static long calNumOfThreads(long waitTime, long serviceTime){
        int numOfCores = Runtime.getRuntime().availableProcessors();
        return numOfCores * (1 + waitTime/serviceTime);
    }

    public static void main(String[] args) {
        //CPU核心数量
        int numOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println(numOfCores);
    }

}

package com.consult.analysis.service.test.main;

import java.util.concurrent.*;

public class ThreadTest {
    public static void main(String[] args) {
        int corePoolSize = 1;
        int maxPoolSize = 1;
        long keepAliveTime = 60;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(5); // 使用LinkedBlockingQueue作为任务队列
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy(); // 当任务被拒绝时，使用CallerRunsPolicy

        // 创建带有阻塞队列的线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

        // 示例任务
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(i);
                executor.execute(() -> {
                    System.out.println("===进来了");
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task executed by thread: " + Thread.currentThread().getName());
                });
            } catch (RejectedExecutionException e) {
                // 线程池和任务队列都已满，任务被拒绝
                System.out.println("Task rejected, waiting for space to become available...");
                try {
                    // 阻塞等待一段时间后再次尝试提交任务
                    Thread.sleep(1000);
                    executor.execute(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        System.out.println("Task executed by thread: " + Thread.currentThread().getName());
                    });
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        System.out.println(workQueue.size());

        // 关闭线程池
        executor.shutdown();

    }
}

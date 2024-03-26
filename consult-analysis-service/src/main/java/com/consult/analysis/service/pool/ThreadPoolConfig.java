package com.consult.analysis.service.pool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author : laoa
 * @describe : 线程池，注意这里采用的是阻塞队列和不丢失任务的拒绝策略，防止任务丢失，如果造成kakaf消息积压
 *  则需要新增服务器节点，同时需要注意kafka的concurrency配置项
 * @email : laoa@markcoin.net
 */
@Configuration
public class ThreadPoolConfig {

    //指定线程池的线程数量(核心线程)
    public final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() *2;
    //指定线程池可支持的最大线程数(如正在运行的核心线程数+队列已经满的数量+临时线程数量>maximumPoolSize的时候，会丢弃任务并抛出RejectedExecutionException异常)
    public final int MAX_POOL_SIZE = CORE_POOL_SIZE *4 < 128 ? 128 : CORE_POOL_SIZE * 4;
    //指定临时线程的最大存活时间
    private final int KEEP_ALIVE_TIME = 500;
    //时间单位
    public final TimeUnit unit=TimeUnit.SECONDS;
    //工作队列，这里采用阻塞队列,用于进行限流，以便高峰的时候，数据处理不过来，当workQueue数据满了的时候，主线程将等待线程池任务完成
    private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
    //当线程池拒绝接受新任务时，会使用提交任务的线程来执行这个任务,又因为用到了LinkedBlockingQueue，所以主线程会阻塞，这里的主线程为kafka消费线程
    private final RejectedExecutionHandler handler=new ThreadPoolExecutor.CallerRunsPolicy();

    @Bean
    public ExecutorService getThreadPoolExecutor(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                unit, workQueue,
                Executors.defaultThreadFactory(),
                handler);
        return executor;
    }

}

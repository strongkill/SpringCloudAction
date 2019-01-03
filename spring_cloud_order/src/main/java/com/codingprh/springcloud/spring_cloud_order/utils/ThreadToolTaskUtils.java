package com.codingprh.springcloud.spring_cloud_order.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 描述:
 * 使用Spring管理起来的线程池
 *
 * @author codingprh
 * @create 2019-01-02 9:41 AM
 */
@Component
public class ThreadToolTaskUtils {
    private static final int poolSize = Runtime.getRuntime().availableProcessors() * 2;
    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
    private static final RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("spring-cloud-pool-%d").build();

    @Bean(name = "threadPoolTaskExecutor")
    public ExecutorService threadPoolExcutor() {
        return new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, namedThreadFactory, policy);
    }
}
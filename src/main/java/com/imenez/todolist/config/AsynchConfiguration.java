package com.imenez.todolist.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@Slf4j
public class AsynchConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${async.executor.corepoolsize}")
    private int corePoolSize;

    @Value("${async.executor.maxpoolsize}")
    private int maxPoolSize;

    @Value("${async.executor.queuecapacity}")
    private int queueCapacity;


    @Bean(name = "threadPoolTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(applicationName + "-AsynchThread-");
        executor.initialize();

        log.warn("Thread pool created with " + corePoolSize + " threads.");
        return executor;
    }
}

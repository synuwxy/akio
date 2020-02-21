package com.synuwxy.akio.config;

import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author wxy
 */
@Component
public class AkioExecutorSupport extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        return new AkioExecutor(threadPoolTaskExecutor);
    }
}

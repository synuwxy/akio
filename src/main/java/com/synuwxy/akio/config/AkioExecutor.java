package com.synuwxy.akio.config;

import com.synuwxy.akio.trace.Tracer;

import java.util.concurrent.Executor;

public class AkioExecutor implements Executor {

    private Executor executor;

    AkioExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(new AkioRunnable(command, Tracer.getTraceId()));
    }
}

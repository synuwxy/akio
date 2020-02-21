package com.synuwxy.akio.config;

import com.synuwxy.akio.trace.Tracer;

/**
 * @author wxy
 */
public class AkioRunnable implements Runnable {

    private String traceId;

    private Runnable runnable;

    AkioRunnable(Runnable runnable, String traceId) {
        this.traceId = traceId;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        Tracer.setTraceId(traceId);
        runnable.run();
        Tracer.deleteTraceId();
    }
}

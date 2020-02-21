package com.synuwxy.akio.trace;

public class Tracer {

    private static final ThreadLocal<String> TRACELOCAL = new ThreadLocal<>();

    public static String getTraceId() {
        String traceId;
        traceId = TRACELOCAL.get();
        return traceId;
    }

    public static void deleteTraceId() {
        TRACELOCAL.remove();
    }

    public static void setTraceId(String traceId) {
        TRACELOCAL.set(traceId);
    }
}

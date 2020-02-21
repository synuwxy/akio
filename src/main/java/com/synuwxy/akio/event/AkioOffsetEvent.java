package com.synuwxy.akio.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author wxy
 */
public class AkioOffsetEvent extends ApplicationEvent {

    private String traceId;

    public AkioOffsetEvent(Object source, String traceId) {
        super(source);
        this.traceId = traceId;
    }

    public String getTraceId() {
        return traceId;
    }
}

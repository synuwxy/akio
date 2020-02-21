package com.synuwxy.akio.api;

import com.synuwxy.akio.event.AkioHandleEvent;
import com.synuwxy.akio.event.AkioOffsetEvent;
import com.synuwxy.akio.record.AkioRecorder;
import com.synuwxy.akio.sample.common.UUIDUtil;
import com.synuwxy.akio.trace.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author wxy
 */
@Component
@Slf4j
public abstract class AbstractAkioListener<T extends AkioHandleEvent> implements ApplicationListener<T> {

    private final ApplicationContext applicationContext;

    @Autowired
    protected AbstractAkioListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public abstract void handle(T event);

    public abstract void offset(T event);

    public abstract void complete(T event);

    @Override
    public void onApplicationEvent(T event) {
        // TODO 线程池造成的traceId存在
        String traceId = Tracer.getTraceId();
        if (null == traceId) {
            traceId = UUIDUtil.generatorId();
            Tracer.setTraceId(traceId);
        }

        AkioRecorder.register(traceId, new OffsetRecorder<>(this, event));

        try {
            handle(event);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("触发回滚 traceId: {}", traceId);
            applicationContext.publishEvent(new AkioOffsetEvent(this, traceId));
        }
        complete(event);
    }
}
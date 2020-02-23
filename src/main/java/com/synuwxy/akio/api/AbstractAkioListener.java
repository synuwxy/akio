package com.synuwxy.akio.api;

import com.synuwxy.akio.common.utils.ObjectUtil;
import com.synuwxy.akio.event.AkioHandleEvent;
import com.synuwxy.akio.event.AkioOffsetEvent;
import com.synuwxy.akio.record.AkioRecorder;
import com.synuwxy.akio.sample.common.UUIDUtil;
import com.synuwxy.akio.trace.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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

    /**
     * 监听到相应事件之后调用的接口
     * @param event 事件
     */
    public abstract void handle(T event);

    /**
     * 事件执行期间出现意外会自动调用该接口，用于保持数据一致性
     * @param event 事件
     */
    public abstract void offset(T event);

    /**
     * 事件执行完成之后调用的接口
     * @param event 事件
     */
    public abstract void complete(T event);

    @Override
    public void onApplicationEvent(T event) {
        // TODO 线程池造成的traceId存在
        String traceId = Tracer.getTraceId();
        if (null == traceId) {
            traceId = UUIDUtil.generatorId();
            Tracer.setTraceId(traceId);
        }

        AkioRecorder.register(traceId, new OffsetRecorder<>(this, ObjectUtil.clone(event)));

        try {
            handle(ObjectUtil.clone(event));
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("触发回滚 traceId: {}", traceId);
            applicationContext.publishEvent(new AkioOffsetEvent(this));
        }
        complete(ObjectUtil.clone(event));
    }
}
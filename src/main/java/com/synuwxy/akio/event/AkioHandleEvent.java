package com.synuwxy.akio.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author wxy
 */
public class AkioHandleEvent extends ApplicationEvent {

    public AkioHandleEvent(Object source) {
        super(source);
    }
}

package com.synuwxy.akio.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author wxy
 */
public class AkioOffsetEvent extends ApplicationEvent {

    public AkioOffsetEvent(Object source) {
        super(source);
    }

}

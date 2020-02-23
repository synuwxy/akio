package com.synuwxy.akio.event;

import org.springframework.context.ApplicationEvent;

import java.io.*;

/**
 * @author wxy
 */
public class AkioHandleEvent extends ApplicationEvent implements Serializable {

    public AkioHandleEvent(Object source) {
        super(source);
    }
}

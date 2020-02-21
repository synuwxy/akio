package com.synuwxy.akio.api;

import com.synuwxy.akio.event.AkioHandleEvent;

/**
 * @author wxy
 */
public class OffsetRecorder<T extends AkioHandleEvent> {

    private AbstractAkioListener<T> abstractAkioListener;

    private T event;

    OffsetRecorder(AbstractAkioListener<T> abstractAkioListener, T event) {
        this.abstractAkioListener = abstractAkioListener;
        this.event = event;
    }

    public void offset() {
        abstractAkioListener.offset(event);
    }
}

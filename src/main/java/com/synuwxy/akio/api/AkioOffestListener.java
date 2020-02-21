package com.synuwxy.akio.api;

import com.synuwxy.akio.event.AkioOffsetEvent;
import com.synuwxy.akio.record.AkioRecorder;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author wxy
 */
@Component
public class AkioOffestListener implements ApplicationListener<AkioOffsetEvent> {

    @Override
    public void onApplicationEvent(AkioOffsetEvent akioOffsetEvent) {
        AkioRecorder.offsetAll();
    }
}

package com.synuwxy.akio.record;

import com.synuwxy.akio.event.AkioHandleEvent;
import com.synuwxy.akio.event.AkioOffsetEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class AkioRecorderTest {

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void registerTest() {
    }

    @Test
    public void SendEventTest() {
        String traceId = "123465";
        applicationContext.publishEvent(new AkioHandleEvent(this));
        applicationContext.publishEvent(new AkioOffsetEvent(this, traceId));
    }
}

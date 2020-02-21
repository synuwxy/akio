package com.synuwxy.akio.record;

import com.alibaba.fastjson.JSONObject;
import com.synuwxy.akio.api.OffsetRecorder;
import com.synuwxy.akio.trace.Tracer;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author wxy
 */
@Slf4j
public class AkioRecorder {

    private final Map<String, Stack<OffsetRecorder>> recorder = new HashMap<>();

    private static AkioRecorder akioRecorder = new AkioRecorder();

    private AkioRecorder() {

    }

    private Map<String, Stack<OffsetRecorder>> getRecorder() {
        return this.recorder;
    }

    public static void register(String traceId, OffsetRecorder offsetRecorder) {
        Map<String, Stack<OffsetRecorder>> recorder = akioRecorder.getRecorder();
        Stack<OffsetRecorder> stack = recorder.computeIfAbsent(traceId, k -> new Stack<>());
        stack.push(offsetRecorder);
    }

    public static void offsetAll() {
        String traceId = Tracer.getTraceId();
        if (null == traceId) {
            return;
        }

        Map<String, Stack<OffsetRecorder>> recorder = akioRecorder.getRecorder();
        Stack<OffsetRecorder> stack = recorder.get(traceId);
        if (null == stack) {
            return;
        }
        int i = 0;
        while (!stack.empty()) {
            log.info("回滚第{}次", i + 1);
            stack.pop().offset();
            i ++;
        }
    }
}

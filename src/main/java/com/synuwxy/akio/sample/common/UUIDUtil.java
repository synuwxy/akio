package com.synuwxy.akio.sample.common;

import java.util.UUID;

public class UUIDUtil {

    public static String generatorId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

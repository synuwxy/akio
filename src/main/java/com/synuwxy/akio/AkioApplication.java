package com.synuwxy.akio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AkioApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkioApplication.class, args);
    }

}

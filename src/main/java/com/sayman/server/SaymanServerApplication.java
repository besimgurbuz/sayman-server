package com.sayman.server;

import com.sayman.server.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(
        JwtConfig.class
)
public class SaymanServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaymanServerApplication.class, args);
    }

}

package com.texnoera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TexnoeraApplication {

    public static void main(String[] args) {
        SpringApplication.run(TexnoeraApplication.class, args);
    }

}

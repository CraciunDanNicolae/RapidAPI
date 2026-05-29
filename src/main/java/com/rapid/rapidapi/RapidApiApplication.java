package com.rapid.rapidapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@org.springframework.scheduling.annotation.EnableScheduling
@SpringBootApplication
public class RapidApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RapidApiApplication.class, args);
    }

}

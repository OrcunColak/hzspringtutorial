package com.colak.hzspringtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HzSpringTutorialApplication  {

    public static void main(String[] args) {
        SpringApplication.run(HzSpringTutorialApplication.class, args);
    }

}

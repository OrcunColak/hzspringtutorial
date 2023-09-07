package com.colak.hzspringtutorial;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HzSpringTutorialApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(HzSpringTutorialApplication.class);
    @Autowired
    HazelcastInstance hazelcastInstance;

    public static void main(String[] args) {
        SpringApplication.run(HzSpringTutorialApplication.class, args);
    }

    @Override
    public void run(String... args) {
        IMap<Long, String> map = hazelcastInstance.getMap("data");
        for (long i = 0; i < 10; i++) {
            String value = map.get(i);
            logger.info("Map value : {} ", value);
        }
    }
}

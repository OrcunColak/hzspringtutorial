package com.colak.hzspringtutorial.workercache;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/worker")
@Slf4j
public class CachingControllerDemo {

    private static final String WORKER_CACHE = "workers";
    private final ArrayList<Worker> workerList = new ArrayList<>(List.of(new Worker(0, "worker0")));


    // The returned Worker is cached and findById() is not called again
    @Cacheable(value = WORKER_CACHE)
    @GetMapping(path = "/findById")
    public Optional<Worker> findById(int id) {
        log.info("findById");
        return workerList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();

    }

    // The updated Worker is cached and findById() is not called again
    @CachePut(value = WORKER_CACHE, key = "#id")
    @PostMapping(path = "/updateById")
    public Worker updateById(int id, Worker worker) {
        log.info("replaceById");
        workerList.removeIf(w -> worker.getId() == id);
        workerList.add(worker);
        return worker;
    }

    // The new Worker is cached and findById() is not called again
    @CachePut(value = WORKER_CACHE, key = "#id")
    @PostMapping(path = "/addWorker")
    public Worker addWorker(int id, Worker worker) {
        log.info("addWorker");
        workerList.add(worker);
        return worker;
    }
}

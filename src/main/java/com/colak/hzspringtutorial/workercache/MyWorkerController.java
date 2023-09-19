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
public class MyWorkerController {

    private static final String WORKER_CACHE = "workers";
    private final ArrayList<Worker> workerList = new ArrayList<>(List.of(new Worker(0, "worker1")));

    // http://localhost:8080/api/worker/findAll
    @Cacheable(WORKER_CACHE)
    @GetMapping(path = "/findAll")
    public List<Worker> findAll() {
        log.info("findAll");
        return workerList;
    }

    @Cacheable(value = WORKER_CACHE, key = "#id")
    @GetMapping(path = "/findByIndex")
    public Optional<Worker> findByIndex(int id) {
        log.info("findByIndex");
        return workerList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();

    }

    @CachePut(value = WORKER_CACHE, key = "#id")
    @PostMapping(path = "/replaceWorker")
    public void replaceWorker(int id, Worker worker) {
        log.info("replaceWorker");
        workerList.removeIf(w -> worker.getId() == id);
        workerList.add(worker);
    }
}

package com.colak.hzspringtutorial.hibernatecache;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.QueryHint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

@RestController
@RequestMapping("/api/country")
@Slf4j
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;


    // http://localhost:8080/api/country/findAll
    @GetMapping(path = "/findAll")
    @QueryHints({
            @QueryHint(name = HINT_CACHEABLE, value = "true")
    })
    public List<Country> findAll() {
        log.info("findAll");
        return countryRepository.findAll();
    }

    // see https://stackoverflow.com/questions/2461063/how-to-clear-all-hibernate-cache-ehcache-using-spring
    // http://localhost:8080/api/country/clear
    @GetMapping(path = "/clear")
    public void clearHibernateCaches() {
        entityManagerFactory.getCache().unwrap(org.hibernate.Cache.class).evictAllRegions();
    }

    // the 2nd level cache is a key-value store. It only works if you get your entities by id
    // see https://stackoverflow.com/questions/7058843/when-and-how-to-use-hibernate-second-level-cache
    // http://localhost:8080/api/country/findById?id=1
    @GetMapping(path = "/findById")
    public Optional<Country> findById(@RequestParam("id") int id) {
        log.info("findById");
        return countryRepository.findById(id);
    }
}

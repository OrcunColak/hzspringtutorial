package com.colak.hzspringtutorial.hibernatecache;

import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

// See https://blog.frankel.ch/digging-hibernate-query-cache/
@NoRepositoryBean
public interface  QueryCacheCrudRepository<T, ID> extends CrudRepository<T, ID> {

    @QueryHints({
            @QueryHint(name = HINT_CACHEABLE, value = "true")
    })
    List<T> findAll();

}

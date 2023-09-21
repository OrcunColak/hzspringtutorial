package com.colak.hzspringtutorial.hibernatecache;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Getter
@Setter
@Table(name = "countries")
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="country")
public class Country {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Country country = (Country) o;
        return id == country.id && name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Country{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}

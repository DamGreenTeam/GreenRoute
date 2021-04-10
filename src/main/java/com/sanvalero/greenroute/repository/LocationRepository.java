package com.sanvalero.greenroute.repository;

import com.sanvalero.greenroute.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 07/04/2021
 */
@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    Set<Location> findAll();
    Location findByName(String name);
}

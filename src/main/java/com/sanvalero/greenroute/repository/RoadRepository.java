package com.sanvalero.greenroute.repository;

import com.sanvalero.greenroute.domain.Road;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 07/04/2021
 */

@Repository
public interface RoadRepository extends CrudRepository<Road, Long> {

    Set<Road> findAll();
}
